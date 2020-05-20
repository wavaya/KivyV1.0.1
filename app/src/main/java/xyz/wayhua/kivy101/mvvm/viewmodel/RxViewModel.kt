package xyz.wayhua.kivy101.mvvm.viewmodel

import androidx.lifecycle.ViewModel


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import xyz.wayhua.kivy101.ext.coroutineLaunch

import xyz.wayhua.kivy101.rx.SchedulerProvider

/**
 * ViewModel for Coroutines Jobs
 *
 * launch() - launch a Rx request
 * clear all request on stop
 */
abstract class RxViewModel(private val schedulerProvider: SchedulerProvider) : ViewModel() {
    var jobs = mutableListOf<Job>()

    fun launch(code: suspend CoroutineScope.() -> Unit) {
        jobs.add(coroutineLaunch(schedulerProvider.ui()) { code.invoke(this) })
    }

    fun launchIo(code: suspend CoroutineScope.() -> Unit) {
        jobs.add(coroutineLaunch(schedulerProvider.io()) { code.invoke(this) })
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }
}