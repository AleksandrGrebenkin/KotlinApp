package com.github.aleksandrgrebenkin.KotlinApp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.KotlinApp.R
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

            val color = when (note.color) {
                Note.Color.WHITE -> R.color.color_white
                Note.Color.YELLOW -> R.color.color_yellow
                Note.Color.GREEN -> R.color.color_green
                Note.Color.BLUE -> R.color.color_blue
                Note.Color.RED -> R.color.color_red
                Note.Color.VIOLET -> R.color.color_violet
                Note.Color.PINK -> R.color.color_pink
            }

            (this as CardView).setCardBackgroundColor(
                    ResourcesCompat.getColor(resources, color, null))

            setOnClickListener {
                onClickListener?.invoke(note)
            }
        }
    }
}