package com.zhukovme.flickrclient.ui.screens.photoInfo

import com.zhukovme.flickrclient.model.vo.PhotoInfoVo
import com.zhukovme.flickrclient.ui.base.MvpView

interface PhotoInfoView : MvpView {

    fun showPhotoInfo(photoInfoVo: PhotoInfoVo)
}
