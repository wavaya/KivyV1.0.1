package xyz.wayhua.kivy101.ext

import android.app.Activity
import android.content.Context

import android.graphics.Point

import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils

import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import xyz.wayhua.kivy101.BuildConfig
import xyz.wayhua.kivy101.R


import java.io.File

import java.net.MalformedURLException
import java.net.URL

fun Context.myCircularProgressDrawable(): CircularProgressDrawable =
        CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

fun Context.getWindowWidth(): Int {
    val display = (this as Activity).windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)

    return size.x
}



fun String.getUriFromDrawable(scheme: String = "android.resource",
                              applicationId: String = BuildConfig.APPLICATION_ID,
                              resType: String = "drawable"): Uri =
        try {
            Uri.parse("$scheme://$applicationId/$resType/$this")
        } catch (e: NullPointerException) {
            Uri.EMPTY
        }

val getDefaultIcon: Uri? by lazy {
    try {
        "ic_launcher".getUriFromDrawable(resType = "mipmap")
    } catch (e: java.lang.Exception) {
        null
    }
}

fun getUriFromURL(urlString: String?): Uri? =
        try {
            Uri.parse(URL(urlString).toURI().toString())
        } catch (e: MalformedURLException) { // TODO: Malformed | NPE
            getDefaultIcon
        } catch (e: NullPointerException) {
            getDefaultIcon
        }

fun isImage(file: File?): Boolean = arrayOf("jpg", "png", "gif", "jpeg")
        .any { file?.name?.toLowerCase()?.endsWith(it) == true }

fun View.blinking() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_anim))
}

fun View.rotate() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_anim))
}

fun View.slideTop() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_up))
}

fun View.stopAnim() {
    clearAnimation()
}