package com.github.aleksandrgrebenkin.kotlinapp.viewmodel

import androidx.annotation.VisibleForTesting
import com.github.aleksandrgrebenkin.kotlinapp.data.model.NoteResult
import com.github.aleksandrgrebenkin.kotlinapp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class MainViewModel(private val notesRepository: NotesRepository) : BaseViewModel<List<Note>?>() {


    private val repositoryNotes = notesRepository.getNotes()

    init {
        launch {
            repositoryNotes.consumeEach { result ->
                when (result) {
                    is NoteResult.Success<*> -> setData(result.data as? List<Note>)
                    is NoteResult.Error -> setError(result.error)
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        repositoryNotes.cancel()
    }

}