package com.smparkworld.park.extension

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.annotation.Dimension
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlin.math.roundToInt

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl", "roundCorner", requireAll = false)
    fun ImageView.loadImage(url: String?, @Dimension roundCorner: Float?) {

        val options = RequestOptions()
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.DATA)

        val transforms = arrayListOf<Transformation<Bitmap>>().also { transforms ->
            roundCorner?.let { corner ->
                transforms.add(CenterCrop())
                transforms.add(RoundedCorners(corner.roundToInt()))
            }
        }

        if (transforms.isNotEmpty()) {
            options += options.transform(MultiTransformation(transforms))
        }

        Glide.with(this)
            .load(url)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(isVisible: Boolean) {
        this.isVisible = isVisible
    }
}

private operator fun RequestOptions.plusAssign(option: RequestOptions?) {
    option?.let { this.apply(option) }
}