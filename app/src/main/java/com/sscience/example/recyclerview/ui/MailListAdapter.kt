package com.sscience.example.recyclerview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sscience.example.recyclerview.data.Email
import com.sscience.example.recyclerview.data.EmailDiffUtil
import com.sscience.example.recyclerview.databinding.MailItemLayoutBinding

class MailListAdapter(private val emailClickListener: EmailClickListener): ListAdapter<Email,MailListAdapter.MailViewHolder>(EmailDiffUtil()){

    class MailViewHolder private constructor(private val binding: MailItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from (parent: ViewGroup): MailViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val view = MailItemLayoutBinding.inflate(inflater,parent,false)
                return MailViewHolder(view)
            }
        }

        fun bind(data: Email, emailClickListener: EmailClickListener) {
            binding.apply {
                email = data
                clickListener = emailClickListener
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        return MailViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        holder.bind(getItem(position),emailClickListener)
    }

    class EmailClickListener(val clickListener: (email: Email,view: View) -> Unit){
        fun onClick(email: Email,view: View) = clickListener(email,view)
    }

}