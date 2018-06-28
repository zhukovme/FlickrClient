package com.zhukovme.flickrclient.ui.screens.photoInfo

import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.extensions.applySchedulers
import com.zhukovme.flickrclient.interactors.GetPhotoInfoInteractor
import com.zhukovme.flickrclient.ui.base.BasePresenter
import io.reactivex.disposables.Disposable
import timber.log.Timber

class PhotoInfoPresenter(private val view: PhotoInfoView,
                         private val getPhotoInfoInteractor: GetPhotoInfoInteractor) : BasePresenter() {

    private var disposable: Disposable? = null

    fun onCreate(photoId: String) {
        loadPhotoInfo(photoId)
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    fun onImageSaved(uri: String?) {
        view.showSnackbar(R.string.photo_info_image_saved)
    }

    fun onImageSaveError(t: Throwable?) {
        handleError(t)
    }

    private fun loadPhotoInfo(photoId: String) {
        disposable = getPhotoInfoInteractor.getPhotoInfo(photoId)
                .applySchedulers()
                .subscribe(view::showPhotoInfo, this::handleError)
    }

    private fun handleError(t: Throwable?) {
        Timber.d(t)
        view.showSnackbar(R.string.error_unexpected_error)
    }
}
