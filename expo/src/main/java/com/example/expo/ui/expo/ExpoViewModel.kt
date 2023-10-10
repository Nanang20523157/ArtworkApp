package com.example.expo.ui.expo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.artworkapp.R
import com.example.core.domain.usecase.ArtworkUseCase
import javax.inject.Inject

class ExpoViewModel @Inject constructor(
    context: Context,
    artworkUseCase: ArtworkUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = context.getString(R.string.under_development)
    }
    val text: LiveData<String> = _text

    val artExpo = artworkUseCase.getAllArtwork().asLiveData()
}