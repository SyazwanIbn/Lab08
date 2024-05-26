package com.syazwan.lab08

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.syazwan.lab08.databinding.ActivityThanksBinding

class ThanksActivity : AppCompatActivity() {

    private lateinit var binding:ActivityThanksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityThanksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nameTextView.text = intent.getStringExtra("name")
        binding.phoneTextView.text = intent.getStringExtra("phone")
        binding.sizeVerifyTextView.text = intent.getStringExtra("size")
        binding.pickupDateVerifyTextView.text = intent.getStringExtra("date")
        binding.pickupTimeVerifyTextView.text = intent.getStringExtra("time")

        binding.sendBtn.setOnClickListener {
            binding.ratingTextView.text = binding.ratingBar.rating.toString()
        }
    }
}