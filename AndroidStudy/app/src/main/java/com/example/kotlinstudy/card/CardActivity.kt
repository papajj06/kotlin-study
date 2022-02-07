package com.example.kotlinstudy.card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.ActivityCardBinding

class CardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardBinding
    private val cardViewAdapter = CardViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImage()
    }

    private fun setImage() {
        binding.rvVertical.adapter = cardViewAdapter
        addImageList()
    }

    private fun addImageList() {
        cardViewAdapter.submitList(
            listOf(
                CardInfo(
                    image = R.drawable.dice_1,
                    name = "dice1"
                ),
                CardInfo(
                    image = R.drawable.dice_2,
                    name = "dice2"
                ),
                CardInfo(
                    image = R.drawable.dice_3,
                    name = "dice3"
                ),
                CardInfo(
                    image = R.drawable.dice_4,
                    name = "dice4"
                ), CardInfo(
                    image = R.drawable.dice_5,
                    name = "dice5"
                ), CardInfo(
                    image = R.drawable.dice_6,
                    name = "dice6"
                )


            )
        )
    }
}