package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.WeatherApp

import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.WeatherApp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceAPI {
    @GET("2.5/weather")
    fun getWeatherDetails(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): Call<WeatherResponse>
}