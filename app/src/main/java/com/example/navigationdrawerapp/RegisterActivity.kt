package com.example.navigationdrawerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.navigationdrawerapp.R
import com.example.navigationdrawerapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btnNavigateToLogin.setOnClickListener {
            navigateToRegister()
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            //register()
        }
    }

    private fun register() {
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()

        if(validateFields(email, password)) {
            Toast.makeText(this, "Iniciar sesión", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validateFields(email: String, password: String): Boolean {
        return if(email.isBlank() || password.isBlank()) false
        else true
    }

    private fun navigateToRegister() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}