package com.zhukovme.flickrclient.mappers

import com.zhukovme.flickrclient.model.dto.photos.PhotoDto
import com.zhukovme.flickrclient.model.vo.PhotoItemVo

/**
 * Created by Michael Zhukov on 27.06.2018.
 * email: zhukovme@gmail.com
 */
class PhotoMapper {

    fun mapToVo(photoDto: PhotoDto): PhotoItemVo {
        var url: String? = null
        var height: Int? = null
        var width: Int? = null
        if (!photoDto.urlQ.isNullOrBlank()) {
            url = photoDto.urlQ
            height = photoDto.heightQ
            width = photoDto.widthQ
        } else if (!photoDto.urlS.isNullOrBlank()) {
            url = photoDto.urlS
            height = photoDto.heightS
            width = photoDto.widthS
        } else if (!photoDto.urlN.isNullOrBlank()) {
            url = photoDto.urlN
            height = photoDto.heightN
            width = photoDto.widthN
        } else if (!photoDto.urlM.isNullOrBlank()) {
            url = photoDto.urlM
            height = photoDto.heightM
            width = photoDto.widthM
        } else if (!photoDto.urlZ.isNullOrBlank()) {
            url = photoDto.urlZ
            height = photoDto.heightZ
            width = photoDto.widthZ
        } else if (!photoDto.urlC.isNullOrBlank()) {
            url = photoDto.urlC
            height = photoDto.heightC
            width = photoDto.widthC
        } else if (!photoDto.urlL.isNullOrBlank()) {
            url = photoDto.urlL
            height = photoDto.heightL
            width = photoDto.widthL
        } else if (!photoDto.urlO.isNullOrBlank()) {
            url = photoDto.urlO
            height = photoDto.heightO
            width = photoDto.widthO
        }
        return PhotoItemVo(photoDto.id, photoDto.owner, photoDto.title, url, height, width)
    }
}
