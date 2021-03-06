package com.zhukovme.flickrclient.data

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun dataModule() = Kodein.Module("Data") {
    bind<PrefHelper>() with singleton { PrefHelper(instance()) }
    bind<PhotosStore>() with singleton { PhotosStore() }
    bind<SuggestionsStore>() with singleton { SuggestionsStore(instance()) }
}
