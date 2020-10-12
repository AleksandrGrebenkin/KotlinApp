package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aleksandrgrebenkin.KotlinApp.model.IModel
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import com.github.aleksandrgrebenkin.KotlinApp.model.implementation.Model

class MainViewModel : ViewModel() {

    private val model: IModel = Model()
    private val notesLiveData = MutableLiveData<List<Note>>()

    init {
        notesLiveData.value = model.getNotes()
    }

    fun getNotesLiveData(): LiveData<List<Note>> = notesLiveData
}