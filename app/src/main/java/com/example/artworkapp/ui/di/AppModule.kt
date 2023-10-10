package com.example.artworkapp.ui.di

import com.example.core.domain.usecase.ArtworkInteractor
import com.example.core.domain.usecase.ArtworkUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideArtworkUseCase(artworkInteractor: ArtworkInteractor): ArtworkUseCase

}