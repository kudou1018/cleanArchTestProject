package com.tk.domain.usecase

interface UseCaseResultListener<T> {
    fun onStarted()
    fun onCompleted(data: T)
    fun onCancelled()
}