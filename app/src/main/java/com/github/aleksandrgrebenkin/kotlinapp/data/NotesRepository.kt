package com.github.aleksandrgrebenkin.kotlinapp.model.data

import com.github.aleksandrgrebenkin.kotlinapp.data.provider.DataProvider
import com.github.aleksandrgrebenkin.kotlinapp.data.provider.FirestoreDataProvider
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

object NotesRepository {

    private val dataProvider: DataProvider = FirestoreDataProvider()

    fun getNotes() = dataProvider.getNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun getCurrentUser() = dataProvider.getCurrentUser()
}