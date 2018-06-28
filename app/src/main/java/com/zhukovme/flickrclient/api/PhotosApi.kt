package com.zhukovme.flickrclient.api

import com.zhukovme.flickrclient.model.dto.photos.PhotosGetRecentResponse
import com.zhukovme.flickrclient.model.dto.photos.PhotosSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
interface PhotosApi {

    @GET("rest/?method=flickr.photos.getRecent")
    fun getRecent(@Query("extras") extras: String? = null,
                  @Query("per_page") perPage: Int? = null,
                  @Query("page") page: Int? = null): Single<PhotosGetRecentResponse>

    @GET("rest/?method=flickr.photos.search")
    fun search(@Query("extras") extras: String? = null,
               @Query("text") text: String? = null,
               @Query("sort") sort: String? = null,
               @Query("per_page") perPage: Int? = null,
               @Query("page") page: Int? = null): Single<PhotosSearchResponse>
}
