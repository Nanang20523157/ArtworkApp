package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.ArtResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    // Mendefinisikan List<ArtResponse>
    private val artList: MutableList<ArtResponse?> = mutableListOf()

    suspend fun getAllArtwork(): Flow<ApiResponse<List<ArtResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListArt()
                val linkImg = response.config?.iiifUrl

                artList.clear()
                response.data?.forEach {
                    val newData = it?.let { art ->
                        ArtResponse(
                            id = art.id,
                            title = art.title,
                            artworkTypeTitle = art.artworkTypeTitle,
                            artistTitles = art.artistTitles,
                            dateDisplay = art.dateDisplay,
                            description = art.description,
                            imageId = "$linkImg/${art.imageId}/full/843,/0/default.jpg",
                            dimensions = art.dimensions
                        )
                    }
                    artList.add(newData)
                }
                if (!artList.isNullOrEmpty()) {
                    emit(ApiResponse.Success(artList.filterNotNull()))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}