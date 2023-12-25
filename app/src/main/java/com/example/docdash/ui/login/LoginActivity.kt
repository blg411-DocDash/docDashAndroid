package com.example.docdash.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docdash.DummyActivity
import com.example.docdash.R
import com.example.docdash.data.LoginRequest
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

            val loginRequest = LoginRequest(userEmail, password)
            viewModel.login(loginRequest)
        }

        viewModel.loginStatus.observe(this) { loginSuccess ->
            if (loginSuccess) {
                showLoginMessage(this, "Login Success")
                val redirectPage = Intent(this, DummyActivity::class.java)
                startActivity(redirectPage)
            } else {
                showLoginMessage(this, viewModel.loginError.value.toString())
            }
        }

    }

    companion object {
        private fun showLoginMessage(loginActivity: LoginActivity, message: String) {
            Toast.makeText(loginActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}