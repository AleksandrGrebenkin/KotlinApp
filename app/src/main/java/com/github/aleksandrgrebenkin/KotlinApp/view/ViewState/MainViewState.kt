package com.github.aleksandrgrebenkin.KotlinApp.view.ViewState

import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note

class MainViewState (
        val notes: List<Note>? = null,
        error: Throwable? = null
) : BaseViewState<List<Note>?>(notes, error)