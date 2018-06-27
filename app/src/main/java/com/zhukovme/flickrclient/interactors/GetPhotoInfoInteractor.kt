package com.zhukovme.flickrclient.interactors

import com.zhukovme.flickrclient.data.PhotosStore
import com.zhukovme.flickrclient.mappers.PhotoMapper
import com.zhukovme.flickrclient.model.vo.PhotoInfoVo
import io.reactivex.Observable

/**
 * Created by Michael Zhukov on 27.06.2018.
 * email: zhukovme@gmail.com
 */
class GetPhotoInfoInteractor(private val photosStore: PhotosStore,
                             private val photoMapper: PhotoMapper) {

    fun getPhotoInfo(photoId: String): Observable<PhotoInfoVo> =
            Observable.create<PhotoInfoVo> {
                val photo = photosStore.getPhoto(photoId)
                if (photo != null) {
                    it.onNext(photoMapper.toInfoVo(photo))
                    it.onComplete()
                } else {
                    it.onError(NoSuchElementException())
                }
            }
}
