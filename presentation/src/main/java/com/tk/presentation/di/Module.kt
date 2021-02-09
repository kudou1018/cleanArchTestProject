package com.tk.presentation.di

import com.tk.data.repository.WeatherGetRepository
import com.tk.domain.usecase.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class Module {

    @Binds
    abstract fun bindWeatherRepository(weatherGetRepository: WeatherGetRepository): WeatherRepository
}