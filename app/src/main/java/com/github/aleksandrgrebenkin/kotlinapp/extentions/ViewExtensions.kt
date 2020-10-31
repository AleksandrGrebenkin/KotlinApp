package com.github.aleksandrgrebenkin.kotlinapp.extentions

import android.view.View

fun View.dip(value: Int) = context.dip(value)
fun View.dip(value: Float) = context.dip(value)