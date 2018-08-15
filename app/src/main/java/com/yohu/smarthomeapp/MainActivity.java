package com.yohu.smarthomeapp;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.gson.Gson;
import com.yohu.smarthomeapp.databinding.ActivityMainBinding;
import com.yohu.smarthomeapp.http.HttpUtil;
import com.yohu.smarthomeapp.http.inf.IResponse;
import com.yohu.smarthomeapp.http.response.Sk;
import com.yohu.smarthomeapp.http.response.Today;
import com.yohu.smarthomeapp.http.response.WeatherResp;
import com.yohu.smarthomeapp.model.Weather;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
                    weather.setCurrentTemperature("当前：" + sk.getTemp());
                    binding.setWeather(weather);
                }

            }

            @Override
            public void onError(Object error) {

            }
        });
    }
}
