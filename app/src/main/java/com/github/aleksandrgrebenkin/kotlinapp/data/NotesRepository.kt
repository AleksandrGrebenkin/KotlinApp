package com.github.aleksandrgrebenkin.kotlinapp.model.data

import com.github.aleksandrgrebenkin.kotlinapp.data.provider.DataProvider
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

class NotesRepository(val dataProvider: DataProvider) {
    fun getNotes() = dataProvider.getNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun deleteNote(id: String) = dataProvider.deleteNote(id)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun getCurrentUser() = dataProvider.getCurrentUser()
}