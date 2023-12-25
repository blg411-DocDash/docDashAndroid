package com.example.docdash.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docdash.R
import com.example.docdash.data.loginRequest
import com.example.docdash.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usernameEditText = binding.editTextEmailAddress
        val passwordEditText = binding.editTextPassword
        val loginButton = binding.buttonLogin

        loginButton.setOnClickListener {
            val userEmail = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            var loginRequest = loginRequest(userEmail, password)
            viewModel.login(loginRequest)

        }
    }
}