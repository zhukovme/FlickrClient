package com.zhukovme.flickrclient.api

import com.zhukovme.flickrclient.api.dto.photos.PhotosGetInfoResponse
import com.zhukovme.flickrclient.api.dto.photos.PhotosGetRecentResponse
import com.zhukovme.flickrclient.api.dto.photos.PhotosGetSizesResponse
import com.zhukovme.flickrclient.api.dto.photos.PhotosSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
interface PhotosApi {

    @GET("rest/?method=flickr.photos.getRecent")
    fun getRecent(@Query("per_page") perPage: Int? = null,
                  @Query("page") page: Int? = null): Single<PhotosGetRecentResponse>

    @GET("rest/?method=fflickr.photos.search")
    fun search(@Query("tags") tags: String? = null,
               @Query("per_page") perPage: Int? = null,
               @Query("page") page: Int? = null): Single<PhotosSearchResponse>

    @GET("rest/?method=fflickr.photos.search")
    fun getInfo(@Query("photo_id") photoId: String,
                @Query("secret") secret: String? = null): Single<PhotosGetInfoResponse>

    @GET("rest/?method=flickr.photos.getSizes")
    fun getSizes(@Query("photo_id") photoId: String): Single<PhotosGetSizesResponse>
}
