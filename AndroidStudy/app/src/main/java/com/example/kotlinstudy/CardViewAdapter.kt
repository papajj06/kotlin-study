package com.example.kotlinstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinstudy.databinding.ItemCardviewBinding


class CardViewAdapter() :
    ListAdapter<CardInfo, CardViewAdapter.CardViewHolder>(CardViewDiffUtil()) {

    class CardViewHolder(private val binding: ItemCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardInfo: CardInfo) {
            binding.card = cardInfo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        val binding = ItemCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class CardViewDiffUtil : DiffUtil.ItemCallback<CardInfo>() {
        override fun areItemsTheSame(
            oldItem: CardInfo,
            newItem: CardInfo
        ) =
            (oldItem.image == newItem.image)


        override fun areContentsTheSame(
            oldItem: CardInfo,
            newItem: CardInfo
        ) =
            (oldItem == newItem)
    }
}