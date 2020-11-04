package com.github.aleksandrgrebenkin.kotlinapp.view.viewstate

import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

class NoteViewState(
        data: Data = Data(),
        error: Throwable? = null
) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val note: Note? = null, val isDeleted: Boolean = false)
}