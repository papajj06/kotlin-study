package com.example.kotlinstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinstudy.card.CardActivity
import com.example.kotlinstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        clickEvent()
    }

    private fun clickEvent() {
        binding.btnRoll.setOnClickListener {
            rollDice()
        }
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
         startActivity(intent)
        }
    }

    private fun rollDice() {
        val button = dice(6)
        binding.tvDice.text = button.roll().toString()
    }

    class dice(val number: Int) {
        fun roll(): Int {
            return (1..number).random()
        }
    }
}