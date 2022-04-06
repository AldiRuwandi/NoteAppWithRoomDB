package uz.context.notewithroom.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import uz.context.notewithroom.R
import uz.context.notewithroom.adapter.NoteAdapter
import uz.context.notewithroom.model.Notes
import java.text.SimpleDateFormat
import java.util.*

class NotesTakerActivity : AppCompatActivity() {
    private lateinit var editTitle: EditText
    private lateinit var editNote: EditText
    private lateinit var imageSave: ImageView
    private lateinit var imageBack: ImageView
    private lateinit var notes: Notes
    private lateinit var titleText: TextView

    private var isOnlNote = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_taker)

        initViews()

    }

    @SuppressLint("SimpleDateFormat")
    private fun initViews() {
        editNote = findViewById(R.id.editText_notes)
        editTitle = findViewById(R.id.editText_title)
        imageSave = findViewById(R.id.imageView_save)
        imageBack = findViewById(R.id.back_image)
        titleText = findViewById(R.id.title_text)

        imageBack.setOnClickListener {
            finish()
        }

        notes = Notes()

        try {
            notes = intent.getSerializableExtra("old_note") as Notes
            editTitle.setText(notes.title)
            editNote.setText(notes.note)
            titleText.text = notes.title
            isOnlNote = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        imageSave.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val des = editNote.text.toString().trim()

            if (des.isEmpty()) {
                val snackBar = Snackbar.make(it, "Please enter some notes!", Snackbar.LENGTH_LONG)
                snackBar.show()
                return@setOnClickListener
            }
            val simpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
            val date = Date()

            if (!isOnlNote) {
                notes = Notes()
            }
            notes.title = title
            notes.note = des
            notes.date = simpleDateFormat.format(date)

            val intent = Intent()
            intent.putExtra("note", notes)
            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }
}