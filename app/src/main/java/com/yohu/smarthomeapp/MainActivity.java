package com.yohu.smarthomeapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.gson.Gson;
import com.yohu.smarthomeapp.databinding.ActivityMainBinding;
import com.yohu.smarthomeapp.fragment.ContentFragment;
import com.yohu.smarthomeapp.fragment.ImageViewFragment;
import com.yohu.smarthomeapp.http.HttpUtil;
import com.yohu.smarthomeapp.http.inf.IResponse;
import com.yohu.smarthomeapp.http.response.Sk;
import com.yohu.smarthomeapp.http.response.Today;
import com.yohu.smarthomeapp.http.response.WeatherResp;
import com.yohu.smarthomeapp.model.Weather;
import com.yohu.smarthomeapp.services.RabbitMqService;
import com.yohu.smarthomeapp.services.message.RabbitMqMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Intent startIntent = new Intent(this, RabbitMqService.class);

        startService(startIntent);
        EventBus.getDefault().register(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ImageViewFragment imageViewFragment = new ImageViewFragment();
        transaction.replace(R.id.main_content, imageViewFragment);
        transaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String city = null;
        try {
            city = URLEncoder.encode("成都", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpUtil.getInstance(this).get("http://v.juhe.cn/weather/index?format=2&cityname=" + city + "&key=caadd6a567dbd0278c85e78c0225f9a5", new IResponse() {
            @Override
            public void onResponse(String data) {
                Gson gson = new Gson();
                WeatherResp weatherResp = gson.fromJson(data, WeatherResp.class);
                if (weatherResp.getError_code() == 0) {
                    Today today = weatherResp.getResult().getToday();
                    Sk sk = weatherResp.getResult().getSk();
                    Weather weather = new Weather();
                    weather.setCity(today.getCity());
                    weather.setDressingAdvice(today.getDressing_advice());
                    weather.setTemperature(today.getTemperature());
                    weather.setWeather(today.getWeather());
                    weather.setCurrentTemperature("当前温度：" + sk.getTemp() + "℃");
                    switch (today.getWeather_id().getFa()) {
                        case "00":
                            weather.setImage("sun");
                            break;
                        case "01":
                            weather.setImage("cloud");
                            break;
                        case "02":
                            weather.setImage("overcast");
                        case "03":
                        case "04":
                        case "05":
                        case "06":
                        case "07":
                        case "08":
                        case "09":
                        case "10":
                        case "11":
                        case "12":
                        case "13":
                            weather.setImage("rain");
                            break;
                    }

                    binding.setWeather(weather);
                }

            }

            @Override
            public void onError(Object error) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onRabbitMqEvent(RabbitMqMessage rabbitMqMessage) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (rabbitMqMessage.getMsgType()) {
            case 1:
                ContentFragment fragment = new ContentFragment();
                transaction.replace(R.id.main_content, fragment);
                transaction.commit();
                break;
            case 2:
                ImageViewFragment imageViewFragment = new ImageViewFragment();
                transaction.replace(R.id.main_content, imageViewFragment);
                transaction.commit();
                break;
        }

    }
}
