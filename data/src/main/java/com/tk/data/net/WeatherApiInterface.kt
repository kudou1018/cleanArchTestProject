package com.tk.data.net

import com.tk.data.entity.WeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET("weather")
    fun getWeatherList(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Call<WeatherEntity>

    @GET("{png}")
    fun getIcon(@Path("png") path: String): Call<WeatherEntity>
}