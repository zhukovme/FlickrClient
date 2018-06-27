package com.zhukovme.flickrclient.extensions

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import timber.log.Timber

/**
 * Created by Michael Zhukov on 28.06.2018.
 * email: zhukovme@gmail.com
 */
const val STORAGE_PERMISSION_REQUEST = 1

//region Storage

fun Context.checkStoragePermission(): Boolean = isPermissionsGranted(listOf(WRITE_EXTERNAL_STORAGE))

fun Activity.requestStoragePermission(requestCode: Int = STORAGE_PERMISSION_REQUEST) {
    requestPermission(requestCode, WRITE_EXTERNAL_STORAGE)
}

//endregion

fun Activity.requestPermission(requestCode: Int, vararg permissions: String) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

fun Context.isPermissionsGranted(permissions: List<String>): Boolean {
    val grantResults = permissions.map {
        return@map try {
            ContextCompat.checkSelfPermission(this, it)
        } catch (e: Exception) {
            Timber.d(e, "Failure checking permission %s", it)
            PackageManager.PERMISSION_DENIED
        }
    }
    return checkResult(grantResults)
}

fun Context.checkResult(grantResults: List<Int>): Boolean {
    grantResults.forEach { if (it == PackageManager.PERMISSION_DENIED) return false }
    return true
}

