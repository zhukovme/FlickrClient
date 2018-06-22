package com.zhukovme.flickrclient.ui.base

import android.support.annotation.StringRes

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
abstract class BasePresenter

interface MvpView {
    fun showSnackbar(@StringRes message: Int)
    fun showToast(@StringRes message: Int)
}
