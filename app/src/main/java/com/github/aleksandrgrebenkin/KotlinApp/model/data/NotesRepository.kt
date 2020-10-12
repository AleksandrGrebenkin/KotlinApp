package com.github.aleksandrgrebenkin.KotlinApp.model.data

import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

object NotesRepository {
    val notes = listOf<Note>(
            Note("Первая заметка",
            "Текст первой заметки. Kotlin - это магия",
                    0xfff06292 .toInt()),
            Note("Вторая заметка",
                    "Текст второй заметки. Kotlin - это магия",
                    0xff9575cd .toInt()),
            Note("Третья заметка",
                    "Текст третей заметки. Kotlin - это магия",
                    0xff64b5f6 .toInt()),
            Note("Четвертая заметка",
                    "Текст четвертой заметки. Kotlin - это магия",
                    0xff4db6ac .toInt()),
            Note("Пятая заметка",
                    "Текст пятой заметки. Kotlin - это магия",
                    0xffb2ff59 .toInt()),
            Note("Шестая заметка",
                    "Текст шестой заметки. Kotlin - это магия",
                    0xffffeb3b .toInt()),
            Note("Седьямая заметка",
                    "Текст седьмой заметки. Kotlin - это магия",
                    0xffff6e40 .toInt()),
            Note("Восьмая заметка",
                    "Текст восьмой заметки. Kotlin - это магия",
                    0xfff06292 .toInt()),
    )

}