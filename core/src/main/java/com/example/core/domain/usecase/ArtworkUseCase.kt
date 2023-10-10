package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Artwork
import kotlinx.coroutines.flow.Flow

interface ArtworkUseCase {

    fun getAllArtwork(): Flow<Resource<List<Artwork>>>

    fun getFavoriteArtwork(): Flow<List<Artwork>>

    fun setFavoriteArtwork(artwork: Artwork, state: Boolean)

}