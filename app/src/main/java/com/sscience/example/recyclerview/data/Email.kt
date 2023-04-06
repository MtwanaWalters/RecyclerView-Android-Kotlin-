package com.sscience.example.recyclerview.data

import androidx.recyclerview.widget.DiffUtil

data class Email (
    val id: Long,
    val sender: String,
    val subject: String,
    val body: String,
    val isStarred: Boolean = false,
    val mailbox: MailBox = MailBox.INBOX
    )

class EmailDiffUtil: DiffUtil.ItemCallback<Email>(){
    override fun areItemsTheSame(oldItem: Email, newItem: Email): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Email, newItem: Email): Boolean {
        return oldItem == newItem
    }
}