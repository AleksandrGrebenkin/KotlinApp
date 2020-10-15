package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import androidx.lifecycle.ViewModel
import com.github.aleksandrgrebenkin.KotlinApp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

class NoteViewModel() : ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
           NotesRepository.saveNote(it)
        }
    }
}