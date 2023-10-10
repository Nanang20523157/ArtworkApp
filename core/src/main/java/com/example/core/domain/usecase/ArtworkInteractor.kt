package com.example.core.domain.usecase

import com.example.core.domain.model.Artwork
import com.example.core.domain.repository.IArtworkRepository
import javax.inject.Inject

class ArtworkInteractor @Inject constructor(private val artworkRepository: IArtworkRepository) :
    ArtworkUseCase {

    override fun getAllArtwork() = artworkRepository.getAllArtwork()

    override fun getFavoriteArtwork() = artworkRepository.getFavoriteArtwork()

    override fun setFavoriteArtwork(artwork: Artwork, state: Boolean) =
        artworkRepository.setFavoriteArtwork(artwork, state)
}