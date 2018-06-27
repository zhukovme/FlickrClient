package com.zhukovme.flickrclient.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings


/**
 * Created by Michael Zhukov on 28.06.2018.
 * email: zhukovme@gmail.com
 */
fun Context.openAppSettings() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    intent.data = Uri.parse("package:$packageName")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(intent)
}
