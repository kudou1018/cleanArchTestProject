package com.tk.presentation.mapper

import com.tk.domain.usecase.WeatherData
import com.tk.presentation.model.WeatherModel
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherModelDataMapper @Inject constructor() {
    fun transform(data: WeatherData): WeatherModel {
        return WeatherModel(
            data.weather,
            data.tempMin.roundToInt(),
            data.tempMax.roundToInt(),
            data.iconUrl
        )
    }
}