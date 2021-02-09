package com.tk.data.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tk.data.entity.WeatherDataMapper
import com.tk.data.entity.WeatherEntity
import com.tk.data.net.WeatherApi
import com.tk.data.net.WeatherApiInterface
import com.tk.domain.usecase.WeatherData
import com.tk.domain.usecase.repository.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherGetRepository @Inject constructor(var mWeatherDataMapper: WeatherDataMapper) :
    WeatherRepository {
    private var mRetrofit: Retrofit

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        this.mRetrofit = Retrofit.Builder()
            .baseUrl(WeatherApi().BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(getClient())
            .build()
    }

    override fun getCurrentWeather(localName: String): WeatherData {
        val service = this.mRetrofit.create(WeatherApiInterface::class.java)
        // todo
        var weatherData = WeatherData("", 0.0, 0.0, "")
        try {
            val response =
                service.getWeatherList(localName, WeatherApi().API_KEY)
                    .execute()
            if (response.isSuccessful && response.body() is WeatherEntity) {
                weatherData = mWeatherDataMapper.transform(response.body()!!)

            }
        } catch (t: Throwable) {
            t.printStackTrace()
            throw t
        }
        return weatherData
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}