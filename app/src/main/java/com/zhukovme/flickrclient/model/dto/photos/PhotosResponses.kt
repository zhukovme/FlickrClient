package com.zhukovme.flickrclient.model.dto.photos

import com.google.gson.annotations.SerializedName

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
data class PhotosGetRecentResponse(@SerializedName("stat") val status: String?,
                                   @SerializedName("code") val code: Int?,
                                   @SerializedName("message") val errorMessage: String?,
                                   @SerializedName("photos") val photos: PhotosDto?)

data class PhotosSearchResponse(@SerializedName("stat") val status: String?,
                                @SerializedName("code") val code: Int?,
                                @SerializedName("message") val errorMessage: String?,
                                @SerializedName("photos") val photos: PhotosDto?)

data class PhotosGetInfoResponse(@SerializedName("stat") val status: String?,
                                 @SerializedName("code") val code: Int?,
                                 @SerializedName("message") val errorMessage: String?,
                                 @SerializedName("photo") val photoInfo: PhotoInfoDto?)

data class PhotosGetSizesResponse(@SerializedName("stat") val status: String?,
                                  @SerializedName("code") val code: Int?,
                                  @SerializedName("message") val errorMessage: String?,
                                  @SerializedName("sizes") val sizes: SizesDto?)
