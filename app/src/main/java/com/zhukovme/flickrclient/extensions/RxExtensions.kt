package com.zhukovme.flickrclient.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
fun <T : Any> Observable<T>.applySchedulers(): Observable<T> =
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Single<T>.applySchedulers(): Single<T> =
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun Completable.applySchedulers(): Completable =
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.applyMainSchedulers(): Observable<T> =
        this.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Single<T>.applyMainSchedulers(): Single<T> =
        this.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())

fun Completable.applyMainSchedulers(): Completable =
        this.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.applyIoSchedulers(): Observable<T> =
        this.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())

fun <T : Any> Single<T>.applyIoSchedulers(): Single<T> =
        this.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())

fun Completable.applyIoSchedulers(): Completable =
        this.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())

fun <T : Any> Observable<T>.surround(before: () -> Unit, after: () -> Unit): Observable<T> =
        this.doOnSubscribe { before() }
                .doOnTerminate { after() }

fun <T : Any> Single<T>.surround(before: () -> Unit, after: () -> Unit): Single<T> =
        this.doOnSubscribe { before() }
                .doOnSuccess { after() }
                .doOnError { after() }

fun Completable.surround(before: () -> Unit, after: () -> Unit): Completable =
        this.doOnSubscribe { before() }
                .doOnTerminate { after() }

fun Disposable.addTo(disposables: CompositeDisposable?) = disposables?.add(this)

inline fun <T : Any> Observable<T>.doCompletable(crossinline completable: (T) -> Completable): Observable<T> =
        this.flatMap { obj ->
            completable(obj)
                    .andThen(Observable.just(obj))
        }
