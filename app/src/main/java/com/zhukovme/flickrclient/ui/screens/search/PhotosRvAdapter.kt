package com.zhukovme.flickrclient.ui.screens.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.zhukovme.flickrclient.R
import com.zhukovme.flickrclient.extensions.setImageUrl
import com.zhukovme.flickrclient.model.vo.PhotoItemVo
import kotlinx.android.synthetic.main.list_item_photo.view.*

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
class PhotosRvAdapter : RecyclerView.Adapter<PhotosRvAdapter.ViewHolder>() {

    private var photos: MutableList<PhotoItemVo> = mutableListOf()
    var onItemClick: ((PhotoItemVo) -> Unit)? = null

    fun setPhotos(photos: List<PhotoItemVo>) {
        this.photos.clear()
        this.photos.addAll(photos)
        notifyDataSetChanged()
    }

    fun clear() {
        this.photos.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoItem = photos[position]
        holder.bind(photoItem)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(photoItem: PhotoItemVo) {
            showImage(photoItem)
        }

        private fun showImage(photoItem: PhotoItemVo) {
            var options = RequestOptions()
                    .placeholder(android.R.color.darker_gray)
            if (photoItem.width != null && photoItem.height != null) {
                options = options.override(photoItem.width, photoItem.height)
            }
            itemView.iv_photo.setImageUrl(photoItem.url, options)
        }

        override fun onClick(v: View?) {
            val photoItem = photos[adapterPosition]
            onItemClick?.invoke(photoItem)
        }
    }
}
