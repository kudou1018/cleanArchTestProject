package com.tk.presentation.presenter

import com.tk.domain.usecase.GetCurrentWeatherData
import com.tk.domain.usecase.UseCaseResultListener
import com.tk.domain.usecase.WeatherData
import com.tk.presentation.mapper.WeatherModelDataMapper
import com.tk.presentation.view.WeatherDisplayView
import javax.inject.Inject

class WeatherFragmentPresenter @Inject constructor(
    var mMapper: WeatherModelDataMapper,
    var mGetCurrentWeatherData: GetCurrentWeatherData
) : UseCaseResultListener<WeatherData> {
    lateinit var mView: WeatherDisplayView
    lateinit var mLocalName: String

    fun setView(view: WeatherDisplayView) {
        mView = view
    }

    fun setLocalName(name: String) {
        mLocalName = name
    }

    fun resume() {
        mGetCurrentWeatherData.mUseCaseResultListener = this
        mGetCurrentWeatherData.getCurrentWeather(mLocalName)
    }

    override fun onStarted() {
        mView.showProgress()
    }

    override fun onCompleted(data: WeatherData) {
        val weatherDataModel = mMapper.transform(data)
        mView.renderWeatherDisplay(weatherDataModel)
        mView.hideProgress()
    }

    override fun onCancelled() {
        mView.hideProgress()
        mView.showErrorMessage()
    }
}