package com.tk.domain.usecase

import javax.inject.Inject

class WeatherData @Inject constructor(
    val weather: String,
    val tempMin: Double,
    val tempMax: Double,
    val iconUrl: String
)