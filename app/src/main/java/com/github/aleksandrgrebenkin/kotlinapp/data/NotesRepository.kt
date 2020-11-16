package com.github.aleksandrgrebenkin.kotlinapp.model.data

import com.github.aleksandrgrebenkin.kotlinapp.data.provider.DataProvider
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

class NotesRepository(val dataProvider: DataProvider) {
    fun getNotes() = dataProvider.subscribeToNotes()
    suspend fun saveNote(note: Note) = dataProvider.saveNote(note)
    suspend fun deleteNote(id: String) = dataProvider.deleteNote(id)
    suspend fun getNoteById(id: String) = dataProvider.getNoteById(id)
    suspend fun getCurrentUser() = dataProvider.getCurrentUser()
}