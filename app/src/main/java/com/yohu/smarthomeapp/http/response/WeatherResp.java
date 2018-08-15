/**
  * Copyright 2018 bejson.com 
  */
package com.yohu.smarthomeapp.http.response;

/**
 * Auto-generated: 2018-08-15 16:28:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WeatherResp {

    private String resultcode;
    private Result result;
    private int error_code;
    public void setResultcode(String resultcode) {
         this.resultcode = resultcode;
     }
     public String getResultcode() {
         return resultcode;
     }

    public void setResult(Result result) {
         this.result = result;
     }
     public Result getResult() {
         return result;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

}