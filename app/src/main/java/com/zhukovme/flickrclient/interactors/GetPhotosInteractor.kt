package com.zhukovme.flickrclient.interactors

import com.zhukovme.flickrclient.api.PhotosApi
import com.zhukovme.flickrclient.mappers.PhotoMapper
import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
class GetPhotosInteractor(private val photosApi: PhotosApi,
                          private val photoMapper: PhotoMapper) {

    companion object {
        private const val extras = "description,date_upload,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o"
        private const val perPage = 51
    }

    fun getRecent(page: Int): Single<List<PhotoItemVo>> =
            photosApi.getRecent(extras, perPage, page)
                    .map { it.photos?.photos ?: Collections.emptyList() }
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map(photoMapper::mapToVo)
                    .toList()
}
