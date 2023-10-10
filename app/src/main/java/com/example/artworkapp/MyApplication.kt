package com.example.artworkapp

import android.app.Application
import android.content.Context
import com.google.android.gms.common.wrappers.InstantApps
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : SplitCompatApplication()
