package com.github.aleksandrgrebenkin.KotlinApp.view.viewstate

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) :
        BaseViewState<Boolean?>(authenticated, error)