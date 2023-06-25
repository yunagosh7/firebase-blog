package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.navigationdrawerapp.databinding.ActivityCreatePostBinding
import com.example.navigationdrawerapp.model.Post
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference

        binding.btnCreatePost.isEnabled = false
        initListeners()

    }

    private fun initListeners() {
        binding.etPostBody.addTextChangedListener {
            if (validateFields()) {
                activateButton()
            } else {
                disableButton()
            }
        }

        binding.etPostTitle.addTextChangedListener {
            if (validateFields()) {
                activateButton()
            } else {
                disableButton()
            }
        }

        binding.etPostCategory.addTextChangedListener {
            if (validateFields()) {
                activateButton()
            } else {
                disableButton()
            }
        }

        binding.etPostBody.addTextChangedListener {
            if (validateFields()) {
                activateButton()
            } else {
                disableButton()
            }
        }

        binding.ibCreatePostClose.setOnClickListener {
            finish()
        }

    }

    private fun validateFields(): Boolean {
        with(binding) {
            return if (etPostBody.text.isBlank() || etPostTitle.text.isBlank() || etPostCategory.text.isBlank()) false
            else true
        }

    }

    private fun activateButton() {
        with(binding) {
            btnCreatePost.setBackgroundColor(getColor(R.color.purple))
            btnCreatePost.setTextColor(getColor(R.color.white))
            btnCreatePost.isEnabled = true
            btnCreatePost.setOnClickListener {
                createPost()
            }
        }

    }

    private fun createPost() {
        val email = intent.getStringExtra("userEmail")
        val title = binding.etPostTitle.text.toString()
        val body = binding.etPostBody.text.toString()
        val category = binding.etPostCategory.text.toString()
        val currentDate = getCurrentDate()
        val post = Post(
            title = title,
            body = body,
            userEmail = email,
            category = category,
            date = currentDate,
            likes = 0L,
        )
        database.child("posts").push().setValue(post)
        Toast.makeText(this, "Post created", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(Date())
    }

    private fun disableButton() {
        with(binding) {
            btnCreatePost.setBackgroundColor(getColor(R.color.btn_next_off))
            btnCreatePost.setTextColor(getColor(R.color.btn_next_off_text))
            btnCreatePost.isEnabled = false

        }
    }


}