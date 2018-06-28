package com.zhukovme.flickrclient.interactors

import com.zhukovme.flickrclient.data.PhotosStore
import com.zhukovme.flickrclient.mappers.PhotoMapper
import com.zhukovme.flickrclient.model.vo.PhotoInfoVo
import io.reactivex.Single

/**
 * Created by Michael Zhukov on 27.06.2018.
 * email: zhukovme@gmail.com
 */
class GetPhotoInfoInteractor(private val photosStore: PhotosStore,
                             private val photoMapper: PhotoMapper) {

    fun getPhotoInfo(photoId: String): Single<PhotoInfoVo> =
            Single.create<PhotoInfoVo> {
                val photo = photosStore.getPhoto(photoId)
                if (photo != null) {
                    it.onSuccess(photoMapper.toInfoVo(photo))
                } else {
                    it.onError(NoSuchElementException())
                }
            }
}
