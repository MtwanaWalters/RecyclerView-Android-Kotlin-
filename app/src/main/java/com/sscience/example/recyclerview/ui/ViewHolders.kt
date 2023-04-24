package com.sscience.example.recyclerview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sscience.example.recyclerview.data.Email
import com.sscience.example.recyclerview.data.Sender
import com.sscience.example.recyclerview.databinding.EmailHeaderListItemBinding
import com.sscience.example.recyclerview.databinding.EmailRecentsListItemBinding
import com.sscience.example.recyclerview.databinding.MailItemLayoutBinding
import com.sscience.example.recyclerview.databinding.SenderListItemLayoutBinding

class MailViewHolder private constructor(private val binding: MailItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

    companion object{
        fun from (parent: ViewGroup): MailViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val view = MailItemLayoutBinding.inflate(inflater,parent,false)
            return MailViewHolder(view)
        }
    }

    fun bind(data: Email, emailClickListener: MailListAdapter.EmailClickListener) {
        binding.apply {
            email = data
            clickListener = emailClickListener
            executePendingBindings()
        }
    }
}


class HeaderViewHolder private constructor(private val binding: EmailHeaderListItemBinding)
    :RecyclerView.ViewHolder(binding.root){

    companion object{
        fun from(parent: ViewGroup): HeaderViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = EmailHeaderListItemBinding.inflate(
                inflater,parent,false
            )
            return HeaderViewHolder(binding)
        }
    }

    fun bind(item: String){
        binding.apply {
            header = item
            executePendingBindings()
        }
    }

    }


class RecentsViewHolder private constructor(private val binding: EmailRecentsListItemBinding)
    : RecyclerView.ViewHolder(binding.root){

    companion object{
        fun from(parent: ViewGroup): RecentsViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = EmailRecentsListItemBinding.inflate(
                inflater,parent,false
            )
            return RecentsViewHolder(binding)
        }
    }

    fun bind(item: List<Sender>,context: Context){
        binding.apply {
            recentsList.apply{
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                adapter = SenderRecyclerAdapter(item)
            }
            executePendingBindings()
        }
    }

    }

class SenderRecyclerAdapter(data: List<Sender>):RecyclerView.Adapter<SenderRecyclerAdapter.SenderViewHolder>(){
    val items = data

    class SenderViewHolder private constructor(private val binding: SenderListItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup): SenderViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = SenderListItemLayoutBinding.inflate(
                    inflater,parent,false
                )
                return SenderViewHolder(binding)
            }
        }

        fun bind(item: Sender){
            binding.apply {
                sender = item
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SenderViewHolder {
        return SenderViewHolder.from(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: SenderViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

}
