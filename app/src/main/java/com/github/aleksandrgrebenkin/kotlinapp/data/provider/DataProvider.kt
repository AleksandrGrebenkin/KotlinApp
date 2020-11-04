package com.github.aleksandrgrebenkin.kotlinapp.data.provider

import androidx.lifecycle.LiveData
import com.github.aleksandrgrebenkin.kotlinapp.data.entity.User
import com.github.aleksandrgrebenkin.kotlinapp.data.model.NoteResult
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

interface DataProvider {
    fun getNotes(): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun deleteNote(id: String): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}