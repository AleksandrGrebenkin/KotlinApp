package com.github.aleksandrgrebenkin.KotlinApp.model.implementation

import com.github.aleksandrgrebenkin.KotlinApp.model.IModel
import com.github.aleksandrgrebenkin.KotlinApp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

class Model : IModel {
    override fun getNotes(): List<Note> {
        return  NotesRepository.notes
    }
}
