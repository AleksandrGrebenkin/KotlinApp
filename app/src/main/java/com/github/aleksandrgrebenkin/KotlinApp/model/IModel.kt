package com.github.aleksandrgrebenkin.KotlinApp.model

import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

interface IModel {
    fun getNotes() : List<Note>
}