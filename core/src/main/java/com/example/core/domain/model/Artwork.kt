package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artwork(
    var artId: Int,
    var artTitle: String?,
    var artworkType: String?,
    var artist: List<String?>?,
    var dateDisplay: String?,
    var description: String?,
    var imageId: String?,
    var dimensions: String?,
    var isFavorite: Boolean
) : Parcelable