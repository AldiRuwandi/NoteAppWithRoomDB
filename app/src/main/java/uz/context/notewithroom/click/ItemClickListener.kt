package uz.context.notewithroom.click

import androidx.cardview.widget.CardView
import uz.context.notewithroom.model.Notes

interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onLongClick(notes: Notes, cardView: CardView)
}