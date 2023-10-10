package com.example.expo.di

import android.content.Context
import com.example.artworkapp.ui.di.ModuleDependencies
import com.example.core.di.GlideModule
import com.example.expo.ui.expo.ExpoFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ModuleDependencies::class], modules = [GlideModule::class])
interface ExpoComponent {

    fun inject(fragment: ExpoFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder
        fun appDependencies(moduleDependencies: ModuleDependencies): Builder
        fun build(): ExpoComponent
    }

}