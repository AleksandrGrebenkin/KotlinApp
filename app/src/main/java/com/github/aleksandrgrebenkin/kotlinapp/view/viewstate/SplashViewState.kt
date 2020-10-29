package com.github.aleksandrgrebenkin.kotlinapp.view.viewstate

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) :
        BaseViewState<Boolean?>(authenticated, error)