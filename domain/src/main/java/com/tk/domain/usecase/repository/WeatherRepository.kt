package com.tk.domain.usecase.repository

import com.tk.domain.usecase.WeatherData

interface WeatherRepository {
    fun getCurrentWeather(localName: String): WeatherData
}