package com.zhukovme.flickrclient.ui.screens.search

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun searchPhotosModule(activity: SearchPhotosActivity) = Kodein.Module("Search photos") {
    bind<SearchPhotosPresenter>() with singleton { SearchPhotosPresenter(activity, instance()) }
}
