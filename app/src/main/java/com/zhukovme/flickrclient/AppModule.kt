package com.zhukovme.flickrclient

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zhukovme.flickrclient.api.deserializers.DateDeserializer
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import java.util.*

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun appModule() = Kodein.Module("App") {
    bind<Gson>() with singleton { Gson() }
}

fun gson(): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()
