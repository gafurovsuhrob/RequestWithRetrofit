package com.example.postrequest.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postrequest.recyclerview.RecyclerViewAdapter
import com.example.postrequest.model.User
import com.example.postrequest.model.UserList
import com.example.postrequest.databinding.ActivityMainBinding
import com.example.postrequest.edit.CreateNewUser

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
        searchUser()

        binding.createUserbtn.setOnClickListener {
            startActivity(Intent(this, CreateNewUser::class.java))
        }
    }

    private fun searchUser() {
        binding.searchBtn.setOnClickListener {
            if (!TextUtils.isEmpty(binding.searchEt.text.toString())) {
                viewModel.searchUser(binding.searchEt.text.toString())
            } else {
                viewModel.getUserList()
            }
        }

    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)

            adapter = recyclerViewAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getUserListObservables().observe(this, Observer<UserList>{
            if (it == null){
                Toast.makeText(this, "No result found", Toast.LENGTH_SHORT).show()
            } else {
                recyclerViewAdapter.userList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getUserList()
    }

    override fun onItemEditClick(user: User) {
        val intent = Intent(this, CreateNewUser::class.java)
        intent.putExtra("user_id", user.id)
        startActivityForResult(intent, 1000)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) {
            viewModel.getUserList()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
