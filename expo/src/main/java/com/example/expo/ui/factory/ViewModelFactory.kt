package com.example.expo.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.ArtworkUseCase
import com.example.expo.ui.expo.ExpoViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val context: Context,
    private val artworkUseCase: ArtworkUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ExpoViewModel::class.java) -> {
                ExpoViewModel(context, artworkUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}