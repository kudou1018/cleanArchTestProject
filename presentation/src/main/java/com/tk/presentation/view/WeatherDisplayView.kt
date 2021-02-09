package com.tk.presentation.view

import com.tk.presentation.model.WeatherModel

interface WeatherDisplayView {
    fun renderWeatherDisplay(weatherModel: WeatherModel)
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage()
}