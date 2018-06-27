package com.zhukovme.flickrclient.ui.base

import android.os.Build
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.extensions.openAppSettings
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), KodeinAware, MvpView {

    abstract val mainLayout: ViewGroup

    private val parentKodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }
    override val kodein: Kodein by lazy {
        Kodein {
            extend(parentKodein)
            import(depsModule())
        }
    }

    abstract fun depsModule(): Kodein.Module

    override fun showSnackbar(@StringRes message: Int) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun showToast(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
                .show()
    }

    override fun showPermissionError(message: Int) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_settings) { openAppSettings() }
                .show()
    }

    protected fun setupToolbar(toolbar: Toolbar, title: String?, withBackButton: Boolean = false) {
        title?.let { toolbar.title = it }
        setSupportActionBar(toolbar)
        if (withBackButton) {
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun setupToolbar(toolbar: Toolbar, @StringRes title: Int, withBackButton: Boolean = false) {
        setupToolbar(toolbar, getString(title), withBackButton)
    }

    protected fun setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}
