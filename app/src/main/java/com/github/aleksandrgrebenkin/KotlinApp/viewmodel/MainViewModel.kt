package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import androidx.lifecycle.Observer
import com.github.aleksandrgrebenkin.KotlinApp.data.model.NoteResult
import com.github.aleksandrgrebenkin.KotlinApp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import com.github.aleksandrgrebenkin.KotlinApp.view.ViewState.MainViewState

class MainViewModel : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer {result: NoteResult? ->
        result ?: return@Observer
        when (result) {
            is NoteResult.Success<*> -> viewStateLiveData
                    .value = MainViewState(result.data as? List<Note>)
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    private val repositoryNotes = NotesRepository.getNotes()

    init {
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }

}