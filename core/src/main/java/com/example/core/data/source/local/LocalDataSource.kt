package com.example.core.data.source.local

import com.example.core.data.source.local.entity.ArtEntity
import com.example.core.data.source.local.room.ArtworkDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val artworkDao: ArtworkDao) {

    fun getAllArtwork(): Flow<List<ArtEntity>> = artworkDao.getAllArtwork()

    fun getFavoriteArtwork(): Flow<List<ArtEntity>> = artworkDao.getFavoriteArtwork()

    suspend fun insertArtwork(artworkList: List<ArtEntity>) = artworkDao.insertArtwork(artworkList)

    fun setFavoriteArtwork(artwork: ArtEntity, newState: Boolean) {
        artwork.isFavorite = newState
        artworkDao.updateFavoriteArtwork(artwork)
    }
}