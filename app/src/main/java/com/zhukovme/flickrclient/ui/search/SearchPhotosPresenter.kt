package com.zhukovme.flickrclient.ui.search

import com.zhukovme.flickrclient.api.PhotosApi
import com.zhukovme.flickrclient.ui.base.BasePresenter

class SearchPhotosPresenter(private val view: SearchPhotosView,
                            private val photosApi: PhotosApi) : BasePresenter() {

    fun onCreate() {
    }

    fun onDestroy() {
    }
}
