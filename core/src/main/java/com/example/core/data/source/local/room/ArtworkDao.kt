package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.source.local.entity.ArtEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM artwork")
    fun getAllArtwork(): Flow<List<ArtEntity>>

    @Query("SELECT * FROM artwork where isFavorite = 1")
    fun getFavoriteArtwork(): Flow<List<ArtEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArtwork(artwork: List<ArtEntity>)

    @Update
    fun updateFavoriteArtwork(artwork: ArtEntity)
}