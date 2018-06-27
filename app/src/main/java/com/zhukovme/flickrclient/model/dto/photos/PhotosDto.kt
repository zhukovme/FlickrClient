package com.zhukovme.flickrclient.model.dto.photos

import com.google.gson.annotations.SerializedName

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
                    @SerializedName("title") val title: String?,
                    @SerializedName("description") val description: ContentDto?,
                    @SerializedName("dateupload") val dateUpload: Long?, // TODO: Change to Date
//                    @SerializedName("url_sq") val urlSq: String?, // Small square
//                    @SerializedName("height_sq") val heightSq: Int?,
//                    @SerializedName("width_sq") val widthSq: Int?,
                    @SerializedName("url_q") val urlQ: String?, // Large square
                    @SerializedName("height_q") val heightQ: Int?,
                    @SerializedName("width_q") val widthQ: Int?,
//                    @SerializedName("url_t") val urlT: String?, // Thumbnail
//                    @SerializedName("height_t") val heightT: Int?,
//                    @SerializedName("width_t") val widthT: Int?,
                    @SerializedName("url_s") val urlS: String?, // Small
                    @SerializedName("height_s") val heightS: Int?,
                    @SerializedName("width_s") val widthS: Int?,
                    @SerializedName("url_n") val urlN: String?, // Small 320
                    @SerializedName("height_n") val heightN: Int?,
                    @SerializedName("width_n") val widthN: Int?,
                    @SerializedName("url_m") val urlM: String?, // Medium
                    @SerializedName("height_m") val heightM: Int?,
                    @SerializedName("width_m") val widthM: Int?,
                    @SerializedName("url_z") val urlZ: String?, // Medium 640
                    @SerializedName("height_z") val heightZ: Int?,
                    @SerializedName("width_z") val widthZ: Int?,
                    @SerializedName("url_c") val urlC: String?, // Medium 800
                    @SerializedName("height_c") val heightC: Int?,
                    @SerializedName("width_c") val widthC: Int?,
                    @SerializedName("url_l") val urlL: String?, // Large
                    @SerializedName("height_l") val heightL: Int?,
                    @SerializedName("width_l") val widthL: Int?,
                    @SerializedName("url_o") val urlO: String?, // Original
                    @SerializedName("height_o") val heightO: Int?,
                    @SerializedName("width_o") val widthO: Int?)

data class PhotoInfoDto(@SerializedName("id") val id: String?,
                        @SerializedName("owner") val owner: OwnerDto?,
                        @SerializedName("title") val title: ContentDto?,
                        @SerializedName("description") val description: ContentDto?,
                        @SerializedName("views") val views: Int?,
                        @SerializedName("urls") val urls: UrlsDto?)
