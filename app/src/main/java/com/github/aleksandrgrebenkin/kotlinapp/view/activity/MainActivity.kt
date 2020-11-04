package com.github.aleksandrgrebenkin.kotlinapp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.github.aleksandrgrebenkin.kotlinapp.R
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import com.github.aleksandrgrebenkin.kotlinapp.view.adapter.NotesAdapter
import com.github.aleksandrgrebenkin.kotlinapp.view.viewstate.MainViewState
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes = R.layout.activity_main
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)


        rv_notes.layoutManager = LinearLayoutManager(this)
        adapter = NotesAdapter { note ->
            NoteActivity.start(this, note.id)
        }

        rv_notes.adapter = adapter

        fab.setOnClickListener {
            NoteActivity.start(this)
        }

    }

    override fun renderData(data: List<Note>?) {
        data?.let { adapter.notes = it }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
            menuInflater.inflate(R.menu.main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.logout -> showLogoutDialog().let { true }
                else -> false
            }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setPositiveButton(R.string.logout_positive) { dialog, which -> logout() }
                .setNegativeButton(R.string.logout_negative) { dialog, which -> dialog.dismiss() }
                .show()
    }

    private fun logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    SplashActivity.start(this)
                    finish()
                }
    }
}