package com.example.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.ArtworkUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(artworkUseCase: ArtworkUseCase) : ViewModel() {
    val favoriteArtwork = artworkUseCase.getFavoriteArtwork().asLiveData()
}