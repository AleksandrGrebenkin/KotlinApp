package com.github.aleksandrgrebenkin.kotlinapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.github.aleksandrgrebenkin.kotlinapp.R
import com.github.aleksandrgrebenkin.kotlinapp.data.errors.NoAuthException
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<S> : AppCompatActivity(), CoroutineScope {

    companion object {
        private const val RC_SIGN_IN = 3698
    }

    override val coroutineContext: CoroutineContext by lazy { Dispatchers.Main + Job() }
    private lateinit var dataJob: Job
    private lateinit var errorJob: Job

    abstract val viewModel: BaseViewModel<S>
    abstract val layoutRes: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let {
            setContentView(it)
        }
    }

    override fun onStart() {
        super.onStart()
        dataJob = launch {
            viewModel.getViewState().consumeEach {data ->
                renderData(data)
            }
        }

        errorJob = launch {
            viewModel.getErrorChannel().consumeEach { error ->
                renderError(error)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        dataJob.cancel()
        errorJob.cancel()
    }

    abstract fun renderData(data: S)

    protected fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLogin()
            else -> error.message?.let {
                showError(it)
            }
        }
    }

    protected fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        Log.e("APPLICATION", text)
    }

    private fun startLogin() {
        val providers = listOf(AuthUI.IdpConfig.GoogleBuilder().build())

        val intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setLogo(R.drawable.android_robot)
                .setTheme(R.style.LoginTheme)
                .setAvailableProviders(providers)
                .build()

        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK){
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}