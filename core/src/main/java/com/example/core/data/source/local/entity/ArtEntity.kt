package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.source.local.dataclass.ArtistList

@Entity(tableName = "artwork")
data class ArtEntity(
    @PrimaryKey
    @ColumnInfo(name = "artId")
    var artId: Int,

    @ColumnInfo(name = "artTitle")
    var artTitle: String? = null,

    @ColumnInfo(name = "artworkType")
    var artworkType: String? = null,

    @ColumnInfo(name = "artist")
    var artist: ArtistList? = null,

    @ColumnInfo(name = "dateDisplay")
    var dateDisplay: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "image_id")
    var imageId: String? = null,

    @ColumnInfo(name = "dimensions")
    var dimensions: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)