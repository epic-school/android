package com.example.epicdatabaseexample.ui.note_list

import androidx.recyclerview.widget.DiffUtil
import com.example.epicdatabaseexample.db.note.NoteEntity

class NoteDiffUtilCallback : DiffUtil.ItemCallback<NoteEntity>() {

    data class Payload(
        val isCompleteChanged: Boolean
    )

    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }

    //из лекции
    override fun getChangePayload(oldItem: NoteEntity, newItem: NoteEntity): Any? {
        val isCompletedChanged = oldItem.isCompleted != newItem.isCompleted
        return if (isCompletedChanged)
            Payload(isCompletedChanged)
        else null //можно удалить
    }

}