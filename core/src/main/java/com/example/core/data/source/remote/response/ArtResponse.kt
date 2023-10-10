package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArtResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("artwork_type_title")
    val artworkTypeTitle: String? = null,

    @field:SerializedName("artist_titles")
    val artistTitles: List<String?>? = null,

    @field:SerializedName("date_display")
    val dateDisplay: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("image_id")
    val imageId: String? = null,

    @field:SerializedName("dimensions")
    val dimensions: String? = null

)