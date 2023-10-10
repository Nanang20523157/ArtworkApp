package com.example.core.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core.R
import com.example.core.databinding.ItemListArtworkBinding
import com.example.core.domain.model.Artwork
import com.example.core.utils.ArtworkDiffCallback
import com.example.core.utils.DataFormater
import javax.inject.Inject

class ArtworkAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ArtworkAdapter.ListViewHolder>() {

    private var listData = ArrayList<Artwork>()
    var onItemClick: ((Artwork) -> Unit)? = null

    fun setData(newListData: List<Artwork>?) {
        if (newListData == null) return
        val diffResult = DiffUtil.calculateDiff(ArtworkDiffCallback(listData, newListData))
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_artwork, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListArtworkBinding.bind(itemView)
        fun bind(data: Artwork) {
            with(binding) {
                insertImage(binding, data)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    private fun insertImage(binding: ItemListArtworkBinding, data: Artwork) {
        with(binding) {
            artTitle.text = data.artTitle
            createdBy.text = DataFormater.formatArtist(data.artist)
            ivItemImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            glide.load(data.imageId)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.ivItemImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
                        return false // Kembalikan false agar Glide tetap menangani error
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Handle ketika gambar berhasil dimuat di sini
                        // Lakukan sesuatu setelah gambar selesai dimuat
                        binding.ivItemImage.scaleType = ImageView.ScaleType.CENTER_CROP
                        return false // Kembalikan false agar Glide tetap menangani keberhasilan
                    }
                })
                .into(ivItemImage)
        }
    }
}