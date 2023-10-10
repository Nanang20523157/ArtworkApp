package com.example.artworkapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.ArtworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(artworkUseCase: ArtworkUseCase) : ViewModel() {
    val artwork = artworkUseCase.getAllArtwork().asLiveData()
}