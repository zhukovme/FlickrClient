package com.zhukovme.flickrclient.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search_photos.*
import kotlinx.android.synthetic.main.toolbar.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SearchPhotosActivity : BaseActivity(), SearchPhotosView {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchPhotosActivity::class.java))
        }
    }

    override lateinit var mainLayout: CoordinatorLayout

    private val presenter: SearchPhotosPresenter by instance()

    override fun deps(): Kodein.Module = searchPhotosModule(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_photos)
        mainLayout = coordl_main

        setupToolbar(toolbar, R.string.search_photos_toolbar_title)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
