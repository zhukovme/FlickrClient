package com.zhukovme.flickrclient.api.dto.photos

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
data class PhotosDto(@SerializedName("page") val page: Int?,
                     @SerializedName("pages") val pages: Int?,
                     @SerializedName("perpage") val perPage: Int?,
                     @SerializedName("total") val total: Int?,
                     @SerializedName("photo") val photos: List<PhotoDto>?)

data class PhotoDto(@SerializedName("id") val id: String?,
                    @SerializedName("owner") val owner: String?,
                    @SerializedName("title") val title: String?)

data class PhotoInfoDto(@SerializedName("id") val id: String?,
                        @SerializedName("owner") val owner: OwnerDto?,
                        @SerializedName("title") val title: ContentDto?,
                        @SerializedName("description") val description: ContentDto?,
                        @SerializedName("dateuploaded") val dateUploaded: Date?,
                        @SerializedName("views") val views: Int?,
                        @SerializedName("urls") val urls: UrlsDto?)
