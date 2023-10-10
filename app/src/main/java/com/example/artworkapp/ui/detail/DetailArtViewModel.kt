package com.example.artworkapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Artwork
import com.example.core.domain.usecase.ArtworkUseCase
import com.example.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailArtViewModel @Inject constructor(private val artworkUseCase: ArtworkUseCase) :
    ViewModel() {

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    var art: Artwork = Artwork(0, null, null, null, null, null, null, null, false)

    fun setSnackbarText(value: Int) {
        _snackbarText.value = Event(value)
    }

    fun setFavoriteArtwork(artwork: Artwork, newStatus: Boolean) {
        artworkUseCase.setFavoriteArtwork(artwork, newStatus)
        art = artwork
    }

}