package com.zhukovme.flickrclient.mappers

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun mappersModule() = Kodein.Module("Mappers") {
    bind<PhotoMapper>() with singleton { PhotoMapper() }
}
