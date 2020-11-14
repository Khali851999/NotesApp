package com.akshat.notesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akshat.notesapp.R
import com.akshat.notesapp.dao.Note

class NotesAdapter(val context: Context, val notesList: ArrayList<Note>): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var allNotesList = ArrayList<Note>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val noteView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return  NotesViewHolder(noteView)
    }

    override fun getItemCount(): Int {
        return allNotesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = allNotesList[position]
        holder.textView.text = note.text
        holder.deleteButton.setOnClickListener {
            allNotesList.remove(allNotesList[position])
        }
        notifyDataSetChanged()
    }
}