package com.github.aleksandrgrebenkin.kotlinapp.view.viewstate

import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

class NoteViewState(
        note: Note? = null,
        error: Throwable? = null
) : BaseViewState<Note?>(note, error)