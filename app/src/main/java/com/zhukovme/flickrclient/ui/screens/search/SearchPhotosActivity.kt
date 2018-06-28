package com.zhukovme.flickrclient.ui.screens.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.GridLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import com.zhukovme.flickrclient.ui.base.BaseActivity
import com.zhukovme.flickrclient.ui.common.SpacesItemDecoration
import com.zhukovme.flickrclient.ui.common.StringSuggestion
import com.zhukovme.flickrclient.ui.screens.photoInfo.PhotoInfoActivity
import kotlinx.android.synthetic.main.activity_search_photos.*
import kotlinx.android.synthetic.main.toolbar.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SearchPhotosActivity : BaseActivity(), SearchPhotosView {

    companion object {
        private const val PHOTOS_SPAN_COUNT = 3

        fun start(context: Context) {
            context.startActivity(Intent(context, SearchPhotosActivity::class.java))
        }
    }

    override lateinit var mainLayout: CoordinatorLayout
    override fun depsModule(): Kodein.Module = searchPhotosModule(this)

    private val presenter: SearchPhotosPresenter by instance()

    private var rvAdapter: PhotosRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_photos)
        mainLayout = coordl_main

        setupToolbar(toolbar, R.string.search_photos_toolbar_title)
        setupRv()
        setupSearchView()
        setupSwipeRefresh()

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showRefreshing() {
        srl_images.post { srl_images.isRefreshing = true }
    }

    override fun hideRefreshing() {
        srl_images.post { srl_images.isRefreshing = false }
    }

    override fun showPhotos(photos: List<PhotoItemVo>) {
        rvAdapter?.setPhotos(photos)
    }

    override fun setSuggestions(suggestions: List<StringSuggestion>) {
        search_view.swapSuggestions(suggestions)
    }

    override fun showPhotoInfo(photoId: String) {
        PhotoInfoActivity.start(this, photoId)
    }

    private fun setupRv() {
        val llManager = GridLayoutManager(this, PHOTOS_SPAN_COUNT, GridLayoutManager.VERTICAL, false)
        val decoration = SpacesItemDecoration(2)
        rvAdapter = PhotosRvAdapter()
        rvAdapter?.onItemClick = presenter::onPhotoItemClick

        rv_images.layoutManager = llManager
        rv_images.addItemDecoration(decoration)
        rv_images.adapter = rvAdapter
    }

    private fun setupSearchView() {
        search_view.setOnQueryChangeListener { _, newQuery ->
            search_view.swapSuggestions(presenter.loadSuggestions(newQuery))
        }

        search_view.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSearchAction(currentQuery: String?) {
                presenter.onSearchSubmit(currentQuery)
            }

            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                search_view.setSearchText(searchSuggestion?.body)
            }
        })

        search_view.setOnFocusChangeListener(object : FloatingSearchView.OnFocusChangeListener {
            override fun onFocus() {
                search_view.swapSuggestions(presenter.loadSuggestions())
            }

            override fun onFocusCleared() {
            }
        })
    }

    private fun setupSwipeRefresh() {
        srl_images.setOnRefreshListener { presenter.onRefresh(search_view.query) }
    }
}
