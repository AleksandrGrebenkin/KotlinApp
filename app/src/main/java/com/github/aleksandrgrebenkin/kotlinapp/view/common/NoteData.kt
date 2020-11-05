package com.github.aleksandrgrebenkin.kotlinapp.view.common

import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

data class NoteData (val note: Note? = null, val isDeleted: Boolean = false)