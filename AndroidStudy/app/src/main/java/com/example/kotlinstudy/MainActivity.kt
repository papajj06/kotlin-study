package com.example.kotlinstudy

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
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