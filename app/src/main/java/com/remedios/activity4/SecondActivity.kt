package com.remedios.activity4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remedios.activity4.databinding.SecondActivityBinding


class SecondActivity: AppCompatActivity() {
lateinit var binding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = intent
        val correctAnsNo=intent.getStringExtra("correct")
        val totalAnsNo=intent.getStringExtra("total")

        val sharedPreferences:SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString:String? = sharedPreferences.getString("STRING_KEY", null)

        binding.correctAns.text=correctAnsNo
        binding.totalAns.text=totalAnsNo
        binding.nameView.text = savedString

    }

    override fun onBackPressed() {
        var intent= Intent(this,FirstActivity::class.java)
        startActivity(intent)
    }
}