package com.github.aleksandrgrebenkin.KotlinApp.model.data

import androidx.lifecycle.MutableLiveData
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import java.util.*

object NotesRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes = mutableListOf(
            Note(UUID.randomUUID().toString(),
                    "Первая заметка",
                    "Текст первой заметки. Kotlin - это магия",
                    Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),
                    "Вторая заметка",
                    "Текст второй заметки. Kotlin - это магия",
                    Note.Color.BLUE),
            Note(UUID.randomUUID().toString(),
                    "Третья заметка",
                    "Текст третей заметки. Kotlin - это магия",
                    Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),
                    "Четвертая заметка",
                    "Текст четвертой заметки. Kotlin - это магия",
                    Note.Color.PINK),
            Note(UUID.randomUUID().toString(),
                    "Пятая заметка",
                    "Текст пятой заметки. Kotlin - это магия",
                    Note.Color.RED),
            Note(UUID.randomUUID().toString(),
                    "Шестая заметка",
                    "Текст шестой заметки. Kotlin - это магия",
                    Note.Color.VIOLET),
            Note(UUID.randomUUID().toString(),
                    "Седьмая заметка",
                    "Текст седьмой заметки. Kotlin - это магия",
                    Note.Color.YELLOW),
    )

    init {
        notesLiveData.value = notes
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    fun addOrReplace(note: Note) {
        for (i in notes.indices) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

    fun getNotes() = notesLiveData

}