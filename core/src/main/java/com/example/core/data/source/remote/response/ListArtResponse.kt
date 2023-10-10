package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListArtResponse(

    @field:SerializedName("pagination")
    val pagination: Pagination? = null,

    @field:SerializedName("data")
    val data: List<ArtResponse?>? = null,

    @field:SerializedName("config")
    val config: Config? = null

)



