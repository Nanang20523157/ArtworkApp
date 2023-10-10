package com.example.core.data


import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.ArtResponse
import com.example.core.domain.model.Artwork
import com.example.core.domain.repository.IArtworkRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtworkRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IArtworkRepository {

    override fun getAllArtwork(): Flow<Resource<List<Artwork>>> =
        object : NetworkBoundResource<List<Artwork>, List<ArtResponse>>() {
            override fun loadFromDB(): Flow<List<Artwork>> {
                return localDataSource.getAllArtwork().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Artwork>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ArtResponse>>> =
                remoteDataSource.getAllArtwork()

            override suspend fun saveCallResult(data: List<ArtResponse>) {
                val artworkList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertArtwork(artworkList)
            }
        }.asFlow()

    override fun getFavoriteArtwork(): Flow<List<Artwork>> {
        return localDataSource.getFavoriteArtwork().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteArtwork(artwork: Artwork, state: Boolean) {
        val artworkEntity = DataMapper.mapDomainToEntity(artwork)
        appExecutors.diskIO().execute { localDataSource.setFavoriteArtwork(artworkEntity, state) }
    }
}