package com.onwd.challenge.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onwd.challenge.databinding.ActivityDeviceDetailBinding

class DeviceDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDeviceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}