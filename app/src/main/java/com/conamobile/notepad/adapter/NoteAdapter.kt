package com.conamobile.notepad.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.conamobile.notepad.R
import com.conamobile.notepad.click.ItemClickListener
import com.conamobile.notepad.model.Notes
import java.util.*

class NoteAdapter(
    private val context: Context,
    private var list: List<Notes>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list, parent, false)
        return NoteViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = list[position]

        holder.apply {
            textTitle.text = note.title
            textTitle.isSelected = true
            textNotes.text = note.note
            textDate.text = note.date
            textDate.isSelected = true

            if (note.pinned) {
                imagePin.setImageResource(R.drawable.img_pin)
            } else {
                imagePin.setImageResource(0)
            }
            val colorCode = getRandomColor()
            noteContainer.setCardBackgroundColor(itemView.resources.getColor(colorCode, null))
            noteContainer.setOnClickListener {
                listener.onClick(note)
            }
            noteContainer.setOnLongClickListener {
                listener.onLongClick(note, noteContainer)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: ArrayList<Notes>) {
        list = filteredList
        notifyDataSetChanged()
    }

    private fun getRandomColor(): Int {
        val colorsCode: ArrayList<Int> = ArrayList()

        colorsCode.add(R.color.color1)
        colorsCode.add(R.color.color2)
        colorsCode.add(R.color.color3)
        colorsCode.add(R.color.color4)
        colorsCode.add(R.color.color5)
        colorsCode.add(R.color.color6)
        colorsCode.add(R.color.color7)
        colorsCode.add(R.color.color8)
        colorsCode.add(R.color.color9)
        colorsCode.add(R.color.light)

        val random = Random()
        val randomColor = random.nextInt(colorsCode.size)
        return colorsCode[randomColor]
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteContainer: CardView = view.findViewById(R.id.notes_container)
        val textDate: TextView = view.findViewById(R.id.textView_date)
        val textTitle: TextView = view.findViewById(R.id.textView_title)
        val textNotes: TextView = view.findViewById(R.id.textView_notes)
        val imagePin: ImageView = view.findViewById(R.id.imageView_pin)
    }
}