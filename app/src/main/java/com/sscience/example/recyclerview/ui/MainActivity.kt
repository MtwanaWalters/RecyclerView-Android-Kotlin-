package com.sscience.example.recyclerview.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sscience.example.recyclerview.R
import com.sscience.example.recyclerview.data.EmailDataStore
import com.sscience.example.recyclerview.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var _emailAdapter: MailListAdapter? = null
    private val emailAdapter get() = _emailAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initList()
    }

    private fun initList(){
        _emailAdapter = MailListAdapter(emailClickListener ).apply {
            submitList(EmailDataStore.inbox)
        }

        binding.emailList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter = emailAdapter
        }
    }

    private val emailClickListener = MailListAdapter.EmailClickListener(){ email,view ->
        Toast.makeText(this,email.id.toString(),Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _emailAdapter = null
        _binding = null
    }
}