package com.zhukovme.flickrclient

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.zhukovme.flickrclient.api.apiModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import timber.log.Timber

/**
 * Created by Michael Zhukov on 21.06.2018.
 * email: zhukovme@gmail.com
 */
class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<Context>() with singleton { applicationContext }
        import(appModule())
        import(apiModule())
    }

    private var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        if (!setupLeakCanary()) return
        if (BuildConfig.DEBUG) debugInit()
    }

    private fun setupLeakCanary(): Boolean {
        if (LeakCanary.isInAnalyzerProcess(this)) return false
        refWatcher = LeakCanary.install(this)
        return true
    }

    private fun debugInit() {
        Timber.plant(Timber.DebugTree())
    }
}
