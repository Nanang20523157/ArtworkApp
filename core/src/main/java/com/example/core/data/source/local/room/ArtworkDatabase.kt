package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.source.local.converter.ArtistListConverter
import com.example.core.data.source.local.entity.ArtEntity

@Database(entities = [ArtEntity::class], version = 1, exportSchema = false)
@TypeConverters(ArtistListConverter::class) // Daftarkan tipe konverter di sini
abstract class ArtworkDatabase : RoomDatabase() {

    abstract fun artworkDao(): ArtworkDao

}