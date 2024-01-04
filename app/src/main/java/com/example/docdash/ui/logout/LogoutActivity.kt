package com.example.docdash.ui.logout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.docdash.R
import com.example.docdash.databinding.ActivityLogoutBinding
import com.example.docdash.services.ApiConstants
import com.example.docdash.ui.UIstates
import com.example.docdash.ui.login.LoginActivity
import com.example.docdash.ui.myTasks.MyTasksAcitivity
import com.example.docdash.ui.taskPool.TaskPoolActivity

class LogoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val redirectPage = Intent(this, LoginActivity::class.java)
        redirectPage.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        binding = ActivityLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logoutButton = binding.buttonLogout

        val emailText = binding.textNameHolder
        val nameText = binding.textEmailHolder

        emailText.text = ApiConstants.EMAIL ?: "No Email"
        nameText.text = ApiConstants.NAME ?: "No Name"

        logoutButton.setOnClickListener {
            ApiConstants.reset()
            UIstates.reset()
            startActivity(redirectPage)
            // To kill current activity
            finish()
        }

        binding.buttonTaskPool1.setOnClickListener {
            val intent = Intent(this, TaskPoolActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }

        binding.buttonMyTasks1.setOnClickListener {
            val intent = Intent(this, MyTasksAcitivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }


}