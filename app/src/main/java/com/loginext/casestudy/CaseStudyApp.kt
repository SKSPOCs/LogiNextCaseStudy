package com.loginext.casestudy

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class CaseStudyApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .networkObserverEnabled(true)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .build()
    }
}