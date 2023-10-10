package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListArtResponse
import retrofit2.http.GET

interface ApiService {
    @GET("artworks?fields=id,title,image_id,date_display,dimensions,artwork_type_title,artist_titles,description")
    suspend fun getListArt(): ListArtResponse
}