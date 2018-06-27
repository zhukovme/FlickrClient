package com.zhukovme.flickrclient.interactors

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun interactorsModule() = Kodein.Module("Interactors") {
    bind<GetPhotosInteractor>() with singleton { GetPhotosInteractor(instance(), instance(), instance()) }
    bind<GetPhotoInfoInteractor>() with singleton { GetPhotoInfoInteractor(instance(), instance()) }
}
