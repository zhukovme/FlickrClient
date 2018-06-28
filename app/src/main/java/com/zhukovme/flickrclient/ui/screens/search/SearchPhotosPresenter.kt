package com.zhukovme.flickrclient.ui.screens.search

import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.data.SuggestionsStore
import com.zhukovme.flickrclient.extensions.applySchedulers
import com.zhukovme.flickrclient.interactors.GetPhotosInteractor
import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import com.zhukovme.flickrclient.ui.base.BasePresenter
import com.zhukovme.flickrclient.ui.common.StringSuggestion
import io.reactivex.disposables.Disposable
import timber.log.Timber

class SearchPhotosPresenter(private val view: SearchPhotosView,
                            private val getPhotosInteractor: GetPhotosInteractor,
                            private val suggestionsStore: SuggestionsStore) : BasePresenter() {

    private var disposable: Disposable? = null

    fun onCreate() {
        loadPhotos(1)
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    fun onSearchSubmit(query: String?) {
        if (query != null && !query.isBlank()) {
            suggestionsStore.put(StringSuggestion(query))
        }
        loadPhotos(1, query)
    }

    fun onRefresh(query: String? = null) {
        loadPhotos(1, query)
    }

    fun onLoadMore(page: Int, query: String?) {
        loadPhotos(page + 1, query)
    }

    fun onPhotoItemClick(photoItemVo: PhotoItemVo) {
        if (photoItemVo.id != null) {
            view.showPhotoInfo(photoItemVo.id)
        } else {
            view.showSnackbar(R.string.error_unexpected_error)
        }
    }

    fun loadSuggestions(query: String = ""): List<StringSuggestion> =
            suggestionsStore.get { it.body.contains(query, true) }
                    .toList()


    private fun loadPhotos(page: Int, query: String? = null) {
        disposable?.dispose()
        disposable = getPhotosInteractor.loadPhotos(page, query)
                .applySchedulers()
                .doOnSubscribe { view.showRefreshing() }
                .doOnEvent { _, _ -> view.hideRefreshing() }
                .subscribe({ photos ->
                    if (page == 1) {
                        view.setPhotos(photos)
                    } else {
                        view.addPhotos(photos)
                    }
                }, this::handleError)
    }

    private fun handleError(t: Throwable) {
        Timber.d(t)
        view.showSnackbar(R.string.error_unexpected_error)
    }
}
