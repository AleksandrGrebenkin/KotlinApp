package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aleksandrgrebenkin.KotlinApp.view.ViewState.BaseViewState

open class BaseViewModel<T, S : BaseViewState<T>> : ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()
    open fun getViewState(): LiveData<S> = viewStateLiveData
}