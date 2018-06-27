package com.zhukovme.flickrclient.ui.screens.search

import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import com.zhukovme.flickrclient.ui.base.MvpView

interface SearchPhotosView : MvpView {

    fun showPhotos(photos: List<PhotoItemVo>)
}
