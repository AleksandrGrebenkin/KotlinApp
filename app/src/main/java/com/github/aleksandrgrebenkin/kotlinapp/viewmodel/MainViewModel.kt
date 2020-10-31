package com.github.aleksandrgrebenkin.kotlinapp.viewmodel

import androidx.lifecycle.Observer
import com.github.aleksandrgrebenkin.kotlinapp.data.model.NoteResult
import com.github.aleksandrgrebenkin.kotlinapp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import com.github.aleksandrgrebenkin.kotlinapp.view.viewstate.MainViewState

class MainViewModel(private val notesRepository: NotesRepository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer {result: NoteResult? ->
        result ?: return@Observer
        when (result) {
            is NoteResult.Success<*> -> viewStateLiveData
                    .value = MainViewState(result.data as? List<Note>)
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    private val repositoryNotes = notesRepository.getNotes()

    init {
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }

}