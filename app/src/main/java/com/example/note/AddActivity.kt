package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.example.note.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        binding.saveBtn.setOnClickListener {
            val title = binding.createTitle.text.toString()
            val content = binding.createContent.text.toString()
            val note = NoteData(0, title, content)
            db.insertNote(note)
            finish()

            Toast.makeText(this, "Note is successfully saved", Toast.LENGTH_SHORT).show()
        }
    }
}