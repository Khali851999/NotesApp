package com.akshat.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.akshat.notesapp.R
import com.akshat.notesapp.data.dao.Note

class NotesAdapter(val context: Context): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var allNotesList = ArrayList<Note>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
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
            notifyDataSetChanged()
            Toast.makeText(context, "Deleted successfully!!!", Toast.LENGTH_SHORT).show()
        }

    }

    fun updateList(newList: List<Note>){
        allNotesList.clear()
        allNotesList.addAll(newList)
        notifyDataSetChanged()
    }
}