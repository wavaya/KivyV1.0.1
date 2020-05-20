package xyz.wayhua.kivy101.mvvm.databinding

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import xyz.wayhua.kivy101.ext.*


/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 01/03/19
 * dedicated to build social-app
 *
 */

object ViewBinding {
    @SuppressLint(value = ["PrivateResource", "UNCHECKED_CAST"])
    @BindingAdapter(value = ["view:isProgress"], requireAll = false)
    @JvmStatic
    fun View.isVisible(isProgress: Boolean?) {
        if (!isProgress.default) {
            visible
            slideTop()
        } else {
            gone
            stopAnim()
        }
    }
}