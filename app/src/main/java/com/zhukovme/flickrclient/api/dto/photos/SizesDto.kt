package com.zhukovme.flickrclient.api.dto.photos

import com.google.gson.annotations.SerializedName

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
data class SizesDto(@SerializedName("canblog") val canBlog: Boolean?,
                    @SerializedName("canprint") val canPrint: Boolean?,
                    @SerializedName("candownload") val canDownload: Boolean?,
                    @SerializedName("size") val sizes: List<SizeDto>?)

data class SizeDto(@SerializedName("label") val label: String?,
                   @SerializedName("width") val width: Int?,
                   @SerializedName("height") val height: Int?,
                   @SerializedName("source") val source: String?,
                   @SerializedName("url") val url: String?,
                   @SerializedName("media") val media: String?)
