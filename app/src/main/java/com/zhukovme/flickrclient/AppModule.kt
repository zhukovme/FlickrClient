package com.zhukovme.flickrclient

import com.google.gson.Gson
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun appModule() = Kodein.Module("App") {
    bind<Gson>() with singleton { Gson() }
}
