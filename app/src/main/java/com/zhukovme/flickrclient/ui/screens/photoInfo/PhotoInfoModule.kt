package com.zhukovme.flickrclient.ui.screens.photoInfo

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun photoInfoModule(activity: PhotoInfoActivity) = Kodein.Module("Photo info") {
    bind<PhotoInfoPresenter>() with singleton { PhotoInfoPresenter(activity, instance()) }
}
