package com.example.core.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.request.RequestOptions
import com.example.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GlideModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context): RequestManager {
        // Konfigurasi RequestOptions
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder_image) // Gambar placeholder
            .error(R.drawable.error_image) // Gambar kesalahan
            // Konfigurasi lainnya, seperti waktu cache, dll.
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        // Inisialisasi Glide dengan RequestOptions
        return Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    @Singleton
    fun provideMemorySizeCalculator(@ApplicationContext context: Context): MemorySizeCalculator {
        return MemorySizeCalculator.Builder(context)
            .setMemoryCacheScreens(2f) // Jumlah tampilan yang akan digunakan untuk menghitung ukuran cache
            .build()
    }

    @Provides
    @Singleton
    fun provideLruResourceCache(): LruResourceCache {
        return LruResourceCache(MEMORY_CACHE_SIZE_BYTES.toLong())
    }

    companion object {
        // Konstanta untuk ukuran cache
        private const val MEMORY_CACHE_SIZE_BYTES = 1024 * 1024 * 10 // 10 MB
    }

}






