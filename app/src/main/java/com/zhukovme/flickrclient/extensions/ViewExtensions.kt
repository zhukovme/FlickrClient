package com.zhukovme.flickrclient.extensions

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Michael Zhukov on 27.06.2018.
 * email: zhukovme@gmail.com
 */
fun ImageView.setImageUrl(url: String?, options: RequestOptions? = null) {
    val mainOptions = RequestOptions()
    options?.let { mainOptions.apply(options) }
    Glide.with(this.context)
            .load(url)
            .apply(mainOptions)
            .into(this)
}

fun TextView.hideIfBlank() {
    visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
}
