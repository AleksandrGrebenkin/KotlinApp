package com.github.aleksandrgrebenkin.KotlinApp.data.provider

import androidx.lifecycle.LiveData
import com.github.aleksandrgrebenkin.KotlinApp.data.entity.User
import com.github.aleksandrgrebenkin.KotlinApp.data.model.NoteResult
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

interface DataProvider {
    fun getNotes(): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}