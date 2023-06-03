package com.example.navigationdrawerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.navigationdrawerapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btnNavigateToRegister.setOnClickListener {
            navigateToLogin()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        if (!validateFields(email, password)) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Registrar usuario", Toast.LENGTH_SHORT).show()

    }

    private fun validateFields(email: String, password: String): Boolean {
        return if (email.isBlank() || password.isBlank()) false
        else true
    }

    private fun navigateToLogin() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}