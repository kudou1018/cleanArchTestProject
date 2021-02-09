package com.tk.domain.usecase

import com.tk.domain.usecase.repository.WeatherRepository
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetCurrentWeatherDataTest : TestCase() {

    @Mock
    private lateinit var mockRepository: WeatherRepository

    @Mock
    private lateinit var mockListener: UseCaseResultListener<WeatherData>

    private lateinit var mTarget: GetCurrentWeatherData

    public override fun setUp() {
        MockitoAnnotations.initMocks(this)
        mTarget = GetCurrentWeatherData()
        mTarget.mRepository = mockRepository
        mTarget.mUseCaseResultListener = mockListener
        Dispatchers.setMain(Dispatchers.Default)
    }

    public override fun tearDown() {
        reset(mockRepository, mockListener)
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCurrentWeatherError() = runBlockingTest {
        `when`(mockRepository.getCurrentWeather("")).thenAnswer {
            throw Exception()
        }
        mTarget.mRepository = mockRepository
        mTarget.getCurrentWeather("")
        verify(mockListener).onStarted()
        verify(mockRepository).getCurrentWeather("")
        verify(mockListener).onCancelled()
        verifyNoMoreInteractions(mockRepository)
        verifyNoMoreInteractions(mockListener)
    }
}