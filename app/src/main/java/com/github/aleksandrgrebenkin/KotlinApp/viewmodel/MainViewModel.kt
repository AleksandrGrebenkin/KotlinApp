package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aleksandrgrebenkin.KotlinApp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.KotlinApp.view.ViewState.MainViewState

class MainViewModel : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        NotesRepository.getNotes().observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it)
                    ?: MainViewState(it)
        }
    }

    fun getViewState(): LiveData<MainViewState> = viewStateLiveData

}