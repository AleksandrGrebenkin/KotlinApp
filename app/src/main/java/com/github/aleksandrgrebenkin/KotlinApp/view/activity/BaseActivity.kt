package com.github.aleksandrgrebenkin.KotlinApp.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.aleksandrgrebenkin.KotlinApp.view.ViewState.BaseViewState
import com.github.aleksandrgrebenkin.KotlinApp.viewmodel.BaseViewModel

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this, { value ->
            value ?: return@observe
            value.error?.let {
                renderError(it)
                return@observe
            }
            renderData(value.data)
        })
    }

    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable) {
        error.message?.let {
            showError(it)
        }
    }

    protected fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        Log.e("APPLICATION", text)
    }
}