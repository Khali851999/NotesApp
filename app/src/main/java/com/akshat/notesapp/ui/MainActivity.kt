package com.akshat.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshat.notesapp.ui.Adapter.NotesAdapter
import com.akshat.notesapp.R
import com.akshat.notesapp.ViewModel.NotesViewModel
import com.akshat.notesapp.data.dao.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.recyclerview_item.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editText)
        val floatingActionButton: FloatingActionButton = findViewById(R.id.fab)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)

        val adapter = NotesAdapter(this, viewModel)
        recyclerView.adapter = adapter
//        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {

                adapter.updateList(it)
                adapter.notifyDataSetChanged()

        })

        floatingActionButton.setOnClickListener {
            val text: String = editText.text.toString()
            if (text.isNotEmpty()) {
                viewModel.insertNote(Note(text))
                Toast.makeText(this, "${text} inserted", Toast.LENGTH_SHORT).show()
                editText.text = null
            } else {
                editText.setError("Cannot be empty!!!")
            }
        }


    }

}