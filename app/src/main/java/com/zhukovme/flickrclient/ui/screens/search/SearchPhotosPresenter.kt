package com.zhukovme.flickrclient.ui.screens.search

import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.extensions.applySchedulers
import com.zhukovme.flickrclient.interactors.GetPhotosInteractor
import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import com.zhukovme.flickrclient.ui.base.BasePresenter
import io.reactivex.disposables.Disposable
import timber.log.Timber

class SearchPhotosPresenter(private val view: SearchPhotosView,
                            private val getPhotosInteractor: GetPhotosInteractor) : BasePresenter() {

    private var disposable: Disposable? = null

    fun onCreate() {
        disposable = getPhotosInteractor.getRecent(1)
                .applySchedulers()
                .subscribe(view::showPhotos, this::handleError)
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    fun onPhotoItemClick(photoItemVo: PhotoItemVo) {
        if (photoItemVo.id != null) {
            view.showPhotoInfo(photoItemVo.id)
        } else {
            view.showSnackbar(R.string.error_unexpected_error)
        }
    }

    private fun handleError(t: Throwable) {
        Timber.d(t)
        view.showSnackbar(R.string.error_unexpected_error)
    }
}
