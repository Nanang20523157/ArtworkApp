package com.example.artworkapp.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.artworkapp.R
import com.example.artworkapp.databinding.ActivityDetailArtBinding
import com.example.core.domain.model.Artwork
import com.example.core.utils.DataFormater
import com.example.core.utils.Event
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailArtActivity : AppCompatActivity() {

    @Inject
    lateinit var glide: RequestManager
    private lateinit var binding: ActivityDetailArtBinding
    private var statusFavorite: Boolean = false

    private val detailArtViewModel: DetailArtViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailArtwork = intent.getParcelableExtra<Artwork>(EXTRA_DATA)
        showDetailArtwork(detailArtwork)

        val toolbar = binding.toolbarDetail.customToolbar
        val appBarLayout = findViewById<AppBarLayout>(R.id.app_bar)
        setSupportActionBar(toolbar)
        // Aktifkan tombol up/back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Tambahkan listener untuk mengendalikan opacity Toolbar saat menggulir
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = kotlin.math.abs(verticalOffset).toFloat() / maxScroll.toFloat()

            // Sesuaikan opacity Toolbar sesuai dengan persentase pengguliran
            toolbar.alpha = percentage
        })

        detailArtViewModel.snackbarText.observe(this) { showSnackBar(it) }

    }

    private fun showSnackBar(eventMessage: Event<Int>) {
        val message = eventMessage.getContentIfNotHandled() ?: return
        if (!statusFavorite) {
            Snackbar.make(
                findViewById(R.id.coordinator_layout),
                getString(message),
                Snackbar.LENGTH_SHORT
            ).setAction("Undo") {
                statusFavorite = !statusFavorite
                detailArtViewModel.setFavoriteArtwork(detailArtViewModel.art, statusFavorite)
                setStatusFavorite(statusFavorite)
            }.show()
        } else {
            Snackbar.make(
                findViewById(R.id.coordinator_layout),
                getString(message),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun showDetailArtwork(detailArtwork: Artwork?) {
        detailArtwork?.let {
            with(binding) {
                detailArtTitle.text = detailArtwork.artTitle
                detailCreatedBy.text = DataFormater.formatArtist(detailArtwork.artist)
                if (detailArtwork.description.isNullOrEmpty()) {
                    content.cardDescription.visibility = View.GONE
                } else content.descriptionContent.text =
                    DataFormater.formatDesc(detailArtwork.description as String)
                content.dimensionsContent.text = detailArtwork.dimensions
                content.textCategory.text = detailArtwork.artworkType
                content.displayText.text = detailArtwork.dateDisplay
                ivItemImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
                glide.load(detailArtwork.imageId)
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

                statusFavorite = detailArtwork.isFavorite
                setStatusFavorite(statusFavorite)

                fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailArtViewModel.setFavoriteArtwork(detailArtwork, statusFavorite)
                    setStatusFavorite(statusFavorite)

                    if (statusFavorite) detailArtViewModel.setSnackbarText(R.string.added_favorite)
                    else detailArtViewModel.setSnackbarText(R.string.removed_favorites)

                }
            }
        }
    }

    // Tangani ketika tombol "Back" ditekan
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_unfavorite
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}