package com.github.aleksandrgrebenkin.KotlinApp.view.activity

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.github.aleksandrgrebenkin.KotlinApp.view.viewstate.SplashViewState
import com.github.aleksandrgrebenkin.KotlinApp.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    companion object {
        fun start(context: Context) = Intent(context, SplashActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val viewModel by lazy { ViewModelProvider(this).get(SplashViewModel::class.java) }
    override val layoutRes = null

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let { startMainActivity() }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}