package com.example.artworkapp.ui.di

import com.example.core.domain.usecase.ArtworkUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ModuleDependencies {

    fun artworkUseCase(): ArtworkUseCase
}