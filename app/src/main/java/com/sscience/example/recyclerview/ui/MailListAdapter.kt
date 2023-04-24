package com.sscience.example.recyclerview.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sscience.example.recyclerview.data.Email
import com.sscience.example.recyclerview.data.Sender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

private const val TYPE_EMAIL = 1
private const val TYPE_HEADER = 0
private const val TYPE_RECENTS = 2

class MailListAdapter(private val emailClickListener: EmailClickListener): ListAdapter<EmailItemDelegate, RecyclerView.ViewHolder>(EmailDelegateDiffUtil()){

    private val defaultDispatcherScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_EMAIL ->  MailViewHolder.from(parent)
            TYPE_HEADER -> HeaderViewHolder.from(parent)
            TYPE_RECENTS -> RecentsViewHolder.from(parent)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MailViewHolder -> {
                val item = getItem(position) as EmailItemDelegate.EmailItem
                holder.bind(item.email,emailClickListener)
            }
            is HeaderViewHolder -> {
                val item = getItem(position) as EmailItemDelegate.HeaderItem
                holder.bind(item.title)
            }
            is RecentsViewHolder -> {
                val item = getItem(position) as EmailItemDelegate.RecentsItem
                holder.bind(item.senders,holder.itemView.context)
            }
        }
    }

    class EmailClickListener(val clickListener: (email: Email, view: View) -> Unit){
        fun onClick(email: Email,view: View) = clickListener(email,view)
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is EmailItemDelegate.EmailItem -> TYPE_EMAIL
            is EmailItemDelegate.HeaderItem -> TYPE_HEADER
            is EmailItemDelegate.RecentsItem -> TYPE_RECENTS
        }
    }

    fun formatAndSubmit(inbox: List<Email>, recents: List<Sender>){
        defaultDispatcherScope.launch {

            val data = listOf(EmailItemDelegate.HeaderItem("Recents")) +
                    EmailItemDelegate.RecentsItem(recents) +
                    listOf(EmailItemDelegate.HeaderItem("Inbox")) +
                    inbox.map{ email -> EmailItemDelegate.EmailItem(email) }

            withContext(Dispatchers.Main){
                submitList(data)
            }

        }

    }
}

sealed class EmailItemDelegate{
    data class EmailItem (val email: Email) : EmailItemDelegate() {
        override val id = email.id
    }

    data class HeaderItem(val title: String) : EmailItemDelegate() {
        override val id = Long.MIN_VALUE
    }

    data class RecentsItem(val senders: List<Sender>) : EmailItemDelegate() {
        override val id = Long.MAX_VALUE
    }

    abstract val id: Long
}

class EmailDelegateDiffUtil: DiffUtil.ItemCallback<EmailItemDelegate>(){
    override fun areItemsTheSame(oldItem: EmailItemDelegate, newItem: EmailItemDelegate): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EmailItemDelegate,
        newItem: EmailItemDelegate
    ): Boolean {
        return oldItem == newItem
    }
}