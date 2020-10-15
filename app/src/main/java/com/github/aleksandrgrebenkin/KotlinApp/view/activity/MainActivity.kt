package com.github.aleksandrgrebenkin.KotlinApp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.KotlinApp.R
import com.github.aleksandrgrebenkin.KotlinApp.view.adapter.NotesAdapter
import com.github.aleksandrgrebenkin.KotlinApp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        rv_notes.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter {
            NoteActivity.start(this, it)
        }
        rv_notes.adapter = notesAdapter

        viewModel.getViewState().observe(this, {
            it?.let { notesAdapter.notes = it.notes }
        })

        fab.setOnClickListener {
            NoteActivity.start(this)
        }

    }
}