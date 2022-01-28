package com.example.kotlinstudy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.kotlinstudy.databinding.ActivityCalculateBinding
import com.example.kotlinstudy.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class CalculateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener { calculateTip() }
        binding.etText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip() {
        val cost = binding.etText.text.toString().toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.radio.checkedRadioButtonId) {
            R.id.btn_amazing -> 0.20
            R.id.btn_good -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (binding.btnSwitch.isChecked) {
            tip = ceil(tip)  // ceil은 상한, 위로만 반올림.
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}