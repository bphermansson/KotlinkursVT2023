package com.paheco.willitrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paheco.willitrain.databinding.ActivityMainBinding
import com.paheco.willitrain.databinding.FragmentStartBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_start)


        /*
        binding = FragmentStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSample.text = "Sample viewBinding"
        binding.btnSample.setOnClickListener{
            binding.tvSample.text = "CLICK!"
        }
        */
    }
}