package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.github.aleksandrgrebenkin.KotlinApp.data.model.NoteResult
import com.github.aleksandrgrebenkin.KotlinApp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import com.github.aleksandrgrebenkin.KotlinApp.view.viewstate.NoteViewState

class NoteViewModel() : BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null
    private var noteLiveData: LiveData<NoteResult>? = null
    private val noteObserver = object : Observer<NoteResult> {
        override fun onChanged(result: NoteResult?) {
            when (result) {
                is NoteResult.Success<*> -> viewStateLiveData.value = NoteViewState(result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
            noteLiveData?.removeObserver(this)
        }
    }

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
        noteLiveData?.removeObserver(noteObserver)
    }

    fun loadNote(id: String) {
        noteLiveData = NotesRepository.getNoteById(id)
        noteLiveData?.observeForever(noteObserver)
    }
}