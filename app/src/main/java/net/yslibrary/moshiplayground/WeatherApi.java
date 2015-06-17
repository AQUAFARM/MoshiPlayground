package net.yslibrary.moshiplayground;

import net.yslibrary.moshiplayground.dto.Weather;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public interface WeatherApi {

    @GET("/forecast/webservice/json/v1")
    void getWeather(@Query("city") int cityCode, Callback<Weather> cb);
}
