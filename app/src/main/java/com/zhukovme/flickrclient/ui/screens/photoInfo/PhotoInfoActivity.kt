package com.zhukovme.flickrclient.ui.screens.photoInfo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator
import com.github.piasy.biv.view.ImageSaveCallback
import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.extensions.*
import com.zhukovme.flickrclient.model.vo.PhotoInfoVo
import com.zhukovme.flickrclient.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo_info.*
import kotlinx.android.synthetic.main.toolbar_transparent.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by Michael Zhukov on 27.06.2018.
 * email: zhukovme@gmail.com
 */
class PhotoInfoActivity : BaseActivity(), PhotoInfoView {

    companion object {
        private const val PHOTO_ID_EXTRA = "photo_id_extra"

        fun start(context: Context, photoId: String) {
            val intent = Intent(context, PhotoInfoActivity::class.java)
            intent.putExtra(PHOTO_ID_EXTRA, photoId)
            context.startActivity(intent)
        }
    }

    override lateinit var mainLayout: RelativeLayout
    override fun depsModule(): Kodein.Module = photoInfoModule(this)

    private val presenter: PhotoInfoPresenter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_info)
        mainLayout = rl_main

        setStatusBarTranslucent()
        setupToolbar(toolbar_transparent, R.string.photo_info_toolbar_title, true)
        setupBigImageView()

        presenter.onCreate(intent.getStringExtra(PHOTO_ID_EXTRA))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.photo_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    true
                }
                R.id.action_save -> {
                    saveImage()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkResult(grantResults.toList())) {
            when (requestCode) {
                STORAGE_PERMISSION_REQUEST -> saveImage()
            }
        } else {
            showPermissionError(R.string.photo_info_error_need_storage_permission)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showPhotoInfo(photoInfoVo: PhotoInfoVo) {
        iv_photo.showImage(Uri.parse(photoInfoVo.url))
        tv_title.text = photoInfoVo.title
        tv_title.hideIfBlank()
        tv_owner.text = getString(R.string.photo_info_by_author, photoInfoVo.owner)
        tv_owner.hideIfBlank()
    }

    private fun saveImage() {
        if (checkStoragePermission()) {
            iv_photo.saveImageIntoGallery()
        } else {
            requestStoragePermission()
        }
    }

    private fun setupBigImageView() {
        iv_photo.setProgressIndicator(ProgressPieIndicator())
        iv_photo.setImageSaveCallback(object : ImageSaveCallback {
            override fun onSuccess(uri: String?) {
                presenter.onImageSaved(uri)
            }

            override fun onFail(t: Throwable?) {
                presenter.onImageSaveError(t)
            }
        })
    }
}
