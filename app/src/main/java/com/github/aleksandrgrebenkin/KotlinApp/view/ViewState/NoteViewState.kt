package com.github.aleksandrgrebenkin.KotlinApp.view.ViewState

import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

class NoteViewState(
        note: Note? = null,
        error: Throwable? = null
) : BaseViewState<Note?>(note, error)