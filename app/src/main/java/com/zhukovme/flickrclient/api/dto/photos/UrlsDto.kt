package com.zhukovme.flickrclient.api.dto.photos

import com.google.gson.annotations.SerializedName

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
data class UrlsDto(@SerializedName("url") val type: List<UrlDto>?)

data class UrlDto(@SerializedName("type") val type: String?,
                  @SerializedName("_content") val url: String?)
