package com.remedios.activity4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.remedios.activity4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.button
        button.setOnClickListener {
            saveData()

        }
    }

    private fun saveData(){
        val insertedText:String = binding.yourName.text.toString().trim()
        val sharedPreferences:SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("STRING_KEY", insertedText)
        }.apply()
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()

        val i = Intent(this, FirstActivity::class.java)
        startActivity(i)

    }
}