package com.zhukovme.flickrclient.data

import com.zhukovme.flickrclient.model.dto.photos.PhotoDto

/**
 * Created by Michael Zhukov on 27.06.2018.
 * email: zhukovme@gmail.com
 */
class PhotosStore {

    private val photosMap: MutableMap<String, PhotoDto> = mutableMapOf()

    fun putPhoto(photo: PhotoDto) = photo.id?.let { photosMap[it] = photo }

    fun putPhotos(photos: List<PhotoDto>) = photos.forEach { putPhoto(it) }

    fun getPhoto(photoId: String): PhotoDto? = photosMap[photoId]

    fun getPhotos(): MutableCollection<PhotoDto> = photosMap.values

    fun clear() = photosMap.clear()
}
