package com.zhukovme.flickrclient.interactors

import com.zhukovme.flickrclient.api.PhotosApi
import com.zhukovme.flickrclient.data.PhotosStore
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
                          private val photosStore: PhotosStore,
                          private val photoMapper: PhotoMapper) {

    companion object {
        private const val EXTRAS = "description,date_upload,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o"
        private const val SORT = "relevance"
        private const val PER_PAGE = 51
    }

    fun getRecent(page: Int): Single<List<PhotoItemVo>> =
            photosApi.getRecent(EXTRAS, PER_PAGE, page)
                    .map { it.photos?.photos ?: Collections.emptyList() }
                    .doOnEvent { photos, _ -> photosStore.putPhotos(photos) }
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map(photoMapper::toVo)
                    .toList()

    fun search(page: Int, query: String?): Single<List<PhotoItemVo>> =
            photosApi.search(EXTRAS, query ?: "", SORT, PER_PAGE, page)
                    .map { it.photos?.photos ?: Collections.emptyList() }
                    .doOnEvent { photos, _ -> photosStore.putPhotos(photos) }
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map(photoMapper::toVo)
                    .toList()
}
