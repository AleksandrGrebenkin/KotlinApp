package com.github.aleksandrgrebenkin.kotlinapp.view.viewstate

import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

class MainViewState (
        val notes: List<Note>? = null,
        error: Throwable? = null
) : BaseViewState<List<Note>?>(notes, error)