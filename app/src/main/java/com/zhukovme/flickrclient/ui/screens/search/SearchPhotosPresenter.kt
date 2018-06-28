package com.zhukovme.flickrclient.ui.screens.search

import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.data.SuggestionsStore
import com.zhukovme.flickrclient.extensions.addTo
import com.zhukovme.flickrclient.extensions.applySchedulers
import com.zhukovme.flickrclient.interactors.GetPhotosInteractor
import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import com.zhukovme.flickrclient.ui.base.BasePresenter
import com.zhukovme.flickrclient.ui.common.StringSuggestion
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class SearchPhotosPresenter(private val view: SearchPhotosView,
                            private val getPhotosInteractor: GetPhotosInteractor,
                            private val suggestionsStore: SuggestionsStore) : BasePresenter() {

    private var page: Int = 1
    private var disposable: CompositeDisposable? = null

    fun onCreate() {
        loadRecent(page)
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    fun onSearchSubmit(query: String?) {
        onRefresh(query)
    }

    fun onRefresh(query: String?) {
        if (query == null || query.isBlank()) {
            loadRecent(page)
        } else {
            suggestionsStore.put(StringSuggestion(query))
            search(page, query)
        }
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

    private fun loadRecent(page: Int) =
            getPhotosInteractor.getRecent(page)
                    .applySchedulers()
                    .doOnSubscribe { view.showRefreshing() }
                    .doOnEvent { _, _ -> view.hideRefreshing() }
                    .subscribe(view::showPhotos, this::handleError)
                    .addTo(disposable)

    private fun search(page: Int, query: String?) =
            getPhotosInteractor.search(page, query)
                    .applySchedulers()
                    .doOnSubscribe { view.showRefreshing() }
                    .doOnEvent { _, _ -> view.hideRefreshing() }
                    .subscribe(view::showPhotos, this::handleError)
                    .addTo(disposable)

    private fun handleError(t: Throwable) {
        Timber.d(t)
        view.showSnackbar(R.string.error_unexpected_error)
    }
}
