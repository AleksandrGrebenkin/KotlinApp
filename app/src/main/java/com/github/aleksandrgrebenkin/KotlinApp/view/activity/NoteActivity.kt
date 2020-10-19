package com.github.aleksandrgrebenkin.KotlinApp.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.github.aleksandrgrebenkin.KotlinApp.R
import com.github.aleksandrgrebenkin.KotlinApp.extentions.DATE_TIME_FORMAT
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import com.github.aleksandrgrebenkin.KotlinApp.view.ViewState.NoteViewState
import com.github.aleksandrgrebenkin.KotlinApp.viewmodel.BaseViewModel
import com.github.aleksandrgrebenkin.KotlinApp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>() {

    companion object {
        private val EXTRA_NOTE = "EXTRA_NOTE"

        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(EXTRA_NOTE, noteId)
            context.startActivity(this)
        }
    }

    override val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_note
    private var note: Note? = null

    val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) = saveNote()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.new_note_title)
        }

        initView()
    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.lastChange?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it)
        } ?: getString(R.string.new_note_title)

        initView()
    }

    private fun initView() {
        et_title.removeTextChangedListener(textChangeListener)
        et_body.removeTextChangedListener(textChangeListener)

        note?.let {
            et_title.setText(it.title)
            et_body.setText(it.body)

            val color = when (it.color) {
                Note.Color.WHITE -> R.color.color_white
                Note.Color.YELLOW -> R.color.color_yellow
                Note.Color.GREEN -> R.color.color_green
                Note.Color.BLUE -> R.color.color_blue
                Note.Color.RED -> R.color.color_red
                Note.Color.VIOLET -> R.color.color_violet
                Note.Color.PINK -> R.color.color_pink
            }

            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return

        note = note?.copy(
                title = et_title.text.toString(),
                body = et_body.text.toString(),
                lastChange = Date()
        ) ?: Note(
                UUID.randomUUID().toString(),
                et_title.text.toString(),
                et_body.text.toString()
        )

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
