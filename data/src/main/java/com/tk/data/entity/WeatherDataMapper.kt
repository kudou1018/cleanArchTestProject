package com.tk.data.entity

import com.tk.data.net.WeatherApi
import com.tk.domain.usecase.WeatherData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataMapper @Inject constructor() {
    private val kelvin = 273.15

    fun transform(weatherEntity: WeatherEntity): WeatherData {
        return WeatherData(
            weatherEntity.weather[0].description,
            weatherEntity.main.temp_min - kelvin,
            weatherEntity.main.temp_max - kelvin,
            WeatherApi().getIconUrl(weatherEntity.weather[0].icon)
        )
    }
}