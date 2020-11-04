package com.github.aleksandrgrebenkin.kotlinapp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.github.aleksandrgrebenkin.kotlinapp.R
import com.github.aleksandrgrebenkin.kotlinapp.extentions.DATE_TIME_FORMAT
import com.github.aleksandrgrebenkin.kotlinapp.extentions.getColorInt
import com.github.aleksandrgrebenkin.kotlinapp.model.data.entity.Note
import com.github.aleksandrgrebenkin.kotlinapp.view.viewstate.NoteViewState
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

    companion object {
        private val EXTRA_NOTE = "EXTRA_NOTE"

        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(EXTRA_NOTE, noteId)
            context.startActivity(this)
        }
    }

    override val viewModel: NoteViewModel by viewModel()
    override val layoutRes = R.layout.activity_note
    private var note: Note? = null
    var color = Note.Color.WHITE

    private val textChangeListener = object : TextWatcher {
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

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) finish()

        this.note = data.note
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

            toolbar.setBackgroundColor(it.color.getColorInt(this))
        }

        color_picker.onColorClickListener = {
            toolbar.setBackgroundColor(it.getColorInt(this))
            color = it
            saveNote()
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return

        note = note?.copy(
                title = et_title.text.toString(),
                body = et_body.text.toString(),
                lastChange = Date(),
                color = color
        ) ?: Note(
                UUID.randomUUID().toString(),
                et_title.text.toString(),
                et_body.text.toString(),
                color = color
        )

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) = menuInflater.inflate(R.menu.note, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        R.id.delete -> deleteNote().let { true }
        R.id.palette -> togglePicker().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        AlertDialog.Builder(this)
                .setTitle(R.string.note_delete_title)
                .setMessage(R.string.note_delete_message)
                .setPositiveButton(R.string.note_delete_ok) { dialog, which ->
                    viewModel.deleteNote()
                }
                .setNegativeButton(R.string.note_delete_cancel) { dialog, which ->
                    dialog.dismiss()
                }
                .show()
    }

    private fun togglePicker() {
        if (color_picker.isOpen) color_picker.close() else color_picker.open()
    }
}
