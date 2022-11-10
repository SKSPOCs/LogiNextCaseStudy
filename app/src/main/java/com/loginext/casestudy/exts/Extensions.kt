package com.loginext.casestudy.exts

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat


fun Context.imageGetter(drawableResourceName: String): Drawable? {
    val identifier = resources.getIdentifier(drawableResourceName, "drawable", packageName)
    val drawable = ResourcesCompat.getDrawable(resources, identifier, null)
    drawable?.let {
        it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
    } ?: run {
        Log.e("DRAWABLE", "Requested Drawable with name \"$drawableResourceName\" is not available")
    }
    return drawable


}
