package com.zhukovme.flickrclient.api.dto.photos

import com.google.gson.annotations.SerializedName

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
data class OwnerDto(@SerializedName("nsid") val nsid: String?,
                    @SerializedName("username") val userName: String?,
                    @SerializedName("realname") val realName: String?,
                    @SerializedName("location") val location: String?,
                    @SerializedName("iconserver") val iconServer: String?,
                    @SerializedName("iconfarm") val iconFarm: Int?,
                    @SerializedName("path_alias") val pathAlias: String?)
