package com.example.docdash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.docdash.databinding.ActivityDummyBinding
import com.example.docdash.services.ApiConstants

class DummyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDummyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        binding = ActivityDummyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textDummy.text = ApiConstants.TOKEN

    }
}