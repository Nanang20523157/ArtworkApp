package com.example.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.Artwork

class ArtworkDiffCallback(
    private val oldList: List<Artwork>,
    private val newList: List<Artwork>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].artId == newList[newItemPosition].artId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}