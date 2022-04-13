package com.conamobile.notepad.click

import androidx.cardview.widget.CardView
import com.conamobile.notepad.model.Notes

interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onLongClick(notes: Notes, cardView: CardView)
}