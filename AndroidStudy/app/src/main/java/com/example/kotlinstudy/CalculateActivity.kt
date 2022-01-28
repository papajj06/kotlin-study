package com.example.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.etText.setOnClickListener{ calculateTip() }
    }

    fun calculateTip(){
        val cost = binding.etText.text.toString().toDouble()
        val checkId = binding.radio.checkedRadioButtonId
        val tipPercentage = when(checkId){
            R.id.btn_amazing -> 0.20
            R.id.btn_good -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.btnSwitch.isChecked
        if(roundUp){
            tip = ceil(tip)  // ceil은 상한, 위로만 반올림.
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvResult.text = getString(R.string.tip_amount, formattedTip)
    }
}