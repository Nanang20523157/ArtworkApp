package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Config(

    @field:SerializedName("website_url")
    val websiteUrl: String? = null,

    @field:SerializedName("iiif_url")
    val iiifUrl: String? = null

)