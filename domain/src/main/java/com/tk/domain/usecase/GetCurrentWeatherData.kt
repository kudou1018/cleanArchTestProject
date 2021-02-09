package com.tk.domain.usecase

import com.tk.domain.usecase.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherData @Inject constructor() {

    lateinit var mUseCaseResultListener: UseCaseResultListener<WeatherData>

    @Inject
    lateinit var mRepository: WeatherRepository

    private val scope = CoroutineScope(Dispatchers.Default)

    fun getCurrentWeather(localName: String) {
        scope.launch {
            getWeatherTask(localName)
        }
    }

    private suspend fun getWeatherTask(localName: String) {
        try {
            withContext(Dispatchers.Main) {
                mUseCaseResultListener.onStarted()
            }

            val weatherData = mRepository.getCurrentWeather(localName)

            withContext(Dispatchers.Main) {
                mUseCaseResultListener.onCompleted(weatherData)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                mUseCaseResultListener.onCancelled()
            }
        }
    }
}