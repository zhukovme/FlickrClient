package com.zhukovme.flickrclient.ui.screens.search

import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import com.zhukovme.flickrclient.ui.base.MvpView
import com.zhukovme.flickrclient.ui.common.StringSuggestion

interface SearchPhotosView : MvpView {

    fun showRefreshing()

    fun hideRefreshing()

    fun setPhotos(photos: List<PhotoItemVo>)

    fun addPhotos(photos: List<PhotoItemVo>)

    fun clearPhotos()

    fun setSuggestions(suggestions: List<StringSuggestion>)

    fun showPhotoInfo(photoId: String)
}
