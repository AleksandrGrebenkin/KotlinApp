package com.github.aleksandrgrebenkin.kotlinapp.viewmodel

import androidx.annotation.VisibleForTesting
import com.github.aleksandrgrebenkin.kotlinapp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import com.github.aleksandrgrebenkin.kotlinapp.view.common.NoteData
import kotlinx.coroutines.launch

class NoteViewModel(val notesRepository: NotesRepository) : BaseViewModel<NoteData>() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(id: String) = launch {
        try {
            notesRepository.getNoteById(id).let { note ->
                pendingNote = note
                setData(NoteData(note = note))
            }
        } catch (e: Throwable) {
            setError(e)
        }
    }

    fun deleteNote() = launch {
        try {
            pendingNote?.let {
                notesRepository.deleteNote(it.id)
            }
            pendingNote = null
            setData(NoteData(isDeleted = true))
        } catch (e: Throwable) {
            setError(e)
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        launch {
            pendingNote?.let { note ->
                notesRepository.saveNote(note)
            }
        }
    }
}