package com.github.aleksandrgrebenkin.kotlinapp.data.provider

import com.github.aleksandrgrebenkin.kotlinapp.data.entity.User
import com.github.aleksandrgrebenkin.kotlinapp.data.model.NoteResult
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {
    fun subscribeToNotes(): ReceiveChannel<NoteResult>
    suspend fun saveNote(note: Note): Note
    suspend fun getNoteById(id: String): Note
    suspend fun deleteNote(id: String)
    suspend fun getCurrentUser(): User?
}