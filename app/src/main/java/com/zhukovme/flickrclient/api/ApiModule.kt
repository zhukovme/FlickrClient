package com.zhukovme.flickrclient.api

import android.content.Context
import com.google.gson.Gson
import com.zhukovme.flickrclient.BuildConfig
import com.zhukovme.flickrclient.api.interceptors.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
private const val BASE_URL = "https://api.flickr.com/services/"

fun apiModule() = Kodein.Module("Api") {
    bind<Retrofit.Builder>() with singleton { retrofitBuilder(instance()) }
    bind<OkHttpClient>() with singleton { okHttpClient(instance()) }
    bind<PhotosApi>() with singleton { photosApi(instance(), instance()) }
}

private fun retrofitBuilder(gson: Gson): Retrofit.Builder =
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)

private fun okHttpClient(context: Context): OkHttpClient {
    val builder = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor(context))
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        builder.addInterceptor(logging)
    }
    return builder.build()
}

private fun photosApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): PhotosApi =
        builder.client(okHttpClient)
                .build()
                .create(PhotosApi::class.java)
