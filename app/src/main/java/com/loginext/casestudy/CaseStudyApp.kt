package com.loginext.casestudy

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class CaseStudyApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this).crossfade(true)
            .placeholder(R.drawable.ic_image_placeholder)
//            .memoryCache {
//                MemoryCache.Builder(this).maxSizePercent(0.5).build()
//            }.diskCache {
//                DiskCache.Builder().maxSizePercent(0.02).build()
//            }
            .build()
    }
}