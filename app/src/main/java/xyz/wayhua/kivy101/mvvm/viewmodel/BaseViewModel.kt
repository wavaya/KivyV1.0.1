package xyz.wayhua.kivy101.mvvm.viewmodel

import androidx.databinding.ObservableField

import xyz.wayhua.kivy101.rx.SchedulerProvider


/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 2019-05-20 for social-app
 */

open class BaseViewModel (
        schedulerProvider: SchedulerProvider
) : RxViewModel(schedulerProvider) {

    val progress = ObservableField<Boolean>(false)
    val isRefreshing = ObservableField<Boolean>(false)
    val isError = ObservableField<Boolean>(false)
    val errMsg = ObservableField<String>("")
}
