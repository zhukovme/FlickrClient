package com.zhukovme.flickrclient.model.dto.photos

import com.google.gson.annotations.SerializedName

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
data class ContentDto(@SerializedName("_content") val content: String?)
