package com.example.postrequest.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.postrequest.model.User
import com.example.postrequest.model.UserResponse
import com.example.postrequest.databinding.ActivityCreateNewUserBinding

class CreateNewUser : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewUserBinding
    private lateinit var viewModel: CreateNewUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user_id = intent.getStringExtra("user_id")

        Toast.makeText(baseContext, "$user_id", Toast.LENGTH_SHORT).show()

        initViewModel()
        createUserObservable()

//        try {
//
//        } catch (e: Exception) {
//            Log.d("", e.message.toString())
//        }

        if (user_id != null) {
            loadUserData(user_id)
        }

        binding.createBtn.setOnClickListener {
            createUser(user_id)
        }

        binding.deleteBtn.setOnClickListener {
            deleteUser(user_id)
        }
    }

    private fun deleteUser(user_id: String?) {
        viewModel.getDeleteUserObserver().observe(this, Observer<UserResponse?> {
            if (it == null) {
                Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Successfully deleted user", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
        viewModel.deleteUser(user_id)
    }

    private fun loadUserData(user_id: String?) {
        viewModel.getLoadUserObserver().observe(this, Observer<UserResponse?> {
            if (it != null) {
                binding.etName.setText(it.data?.name)
                binding.etEmail.setText(it.data?.email)
                binding.createBtn.text = "Update"
                binding.deleteBtn.visibility = VISIBLE
            }
        })
        viewModel.getUserData(user_id)

    }

    private fun createUser(user_id: String?) {
        val user = User(
            "",
            binding.etName.text.toString(),
            binding.etEmail.text.toString(),
            "Active",
            "Male"
        )
        if (user_id == null) {
            viewModel.createUser(user)
        } else {
            viewModel.updateUser(user_id, user)
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CreateNewUserViewModel::class.java]

    }

    private fun createUserObservable() {
        viewModel.getCreateNewUserObserver().observe(this, Observer<UserResponse?> {
            if (it == null) {
                Toast.makeText(this, "Failed to create/update new user", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Successfully created/updated new user", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        })
    }
}