package com.github.aleksandrgrebenkin.kotlinapp.extentions

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.github.aleksandrgrebenkin.kotlinapp.R
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note

fun Note.Color.getColorRes() = when (this) {
    Note.Color.WHITE -> R.color.color_white
    Note.Color.YELLOW -> R.color.color_yellow
    Note.Color.GREEN -> R.color.color_green
    Note.Color.BLUE -> R.color.color_blue
    Note.Color.RED -> R.color.color_red
    Note.Color.VIOLET -> R.color.color_violet
    Note.Color.PINK -> R.color.color_pink
}

fun Note.Color.getColorInt(context: Context)
        = ResourcesCompat.getColor(context.resources, getColorRes(), null)