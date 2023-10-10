package com.example.core.utils

import com.example.core.data.source.local.entity.ArtEntity
import com.example.core.data.source.local.dataclass.ArtistList
import com.example.core.data.source.remote.response.ArtResponse
import com.example.core.domain.model.Artwork

object DataMapper {
    fun mapResponsesToEntities(input: List<ArtResponse>): List<ArtEntity> {
        val artworkList = ArrayList<ArtEntity>()
        input.map {
            val artwork = ArtEntity(
                artId = it.id,
                artTitle = it.title,
                artworkType = it.artworkTypeTitle,
                artist = ArtistList(it.artistTitles),
                dateDisplay = it.dateDisplay,
                description = it.description,
                imageId = it.imageId,
                dimensions = it.dimensions,
                isFavorite = false
            )
            artworkList.add(artwork)
        }
        return artworkList
    }

    fun mapEntitiesToDomain(input: List<ArtEntity>): List<Artwork> =
        input.map {
            Artwork(
                artId = it.artId,
                artTitle = it.artTitle,
                artworkType = it.artworkType,
                artist = it.artist?.list,
                dateDisplay = it.dateDisplay,
                description = it.description,
                imageId = it.imageId,
                dimensions = it.dimensions,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Artwork) = ArtEntity(
        artId = input.artId,
        artTitle = input.artTitle,
        artworkType = input.artworkType,
        artist = ArtistList(input.artist),
        dateDisplay = input.dateDisplay,
        description = input.description,
        imageId = input.imageId,
        dimensions = input.dimensions,
        isFavorite = input.isFavorite
    )
}