package com.github.aleksandrgrebenkin.KotlinApp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.KotlinApp.R
import com.github.aleksandrgrebenkin.KotlinApp.extentions.getColorInt
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(val onClickListener: ((Note) -> Unit)? = null)
    : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_note,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.bind(notes[position])

    override fun getItemCount() = notes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView) {
            note_title.text = note.title
            note_body.text = note.body

            (this as CardView).setCardBackgroundColor(note.color.getColorInt(context))

            setOnClickListener {
                onClickListener?.invoke(note)
            }
        }
    }
}