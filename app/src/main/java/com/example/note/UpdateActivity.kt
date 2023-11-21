package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.note.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updateTitle.setText(note.title)
        binding.updateContent.setText(note.content)

        binding.updateBtn.setOnClickListener {
            val newTitle = binding.updateTitle.text.toString()
            val newContent = binding.updateContent.text.toString()
            val updateNote = NoteData(noteId, newTitle, newContent)
            db.updateNote(updateNote)
            finish()

            Toast.makeText(this, "Note successfully updated", Toast.LENGTH_SHORT).show()
        }

    }
}