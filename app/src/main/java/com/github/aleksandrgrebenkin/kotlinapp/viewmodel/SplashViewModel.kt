package com.github.aleksandrgrebenkin.kotlinapp.viewmodel

import com.github.aleksandrgrebenkin.kotlinapp.data.errors.NoAuthException
import com.github.aleksandrgrebenkin.kotlinapp.model.data.NotesRepository
import kotlinx.coroutines.launch

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?>() {

    fun requestUser() = launch {
        notesRepository.getCurrentUser()?.let {
            setData(true)
        } ?: setError(NoAuthException())
    }
}