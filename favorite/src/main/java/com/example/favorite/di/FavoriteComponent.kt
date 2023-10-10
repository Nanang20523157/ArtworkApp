package com.example.favorite.di

import android.content.Context
import com.example.artworkapp.ui.di.ModuleDependencies
import com.example.core.di.GlideModule
import com.example.favorite.ui.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ModuleDependencies::class], modules = [GlideModule::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder
        fun appDependencies(moduleDependencies: ModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}