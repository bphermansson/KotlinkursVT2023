package com.paheco.viewbindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paheco.viewbindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSample.text = "Sample viewBinding"
        binding.btnSample.setOnClickListener{
            binding.tvSample.text = "CLICK!"
        }
    }
}