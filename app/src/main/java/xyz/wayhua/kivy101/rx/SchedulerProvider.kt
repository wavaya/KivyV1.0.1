package xyz.wayhua.kivy101.rx

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Rx Scheduler Provider
 */
interface SchedulerProvider {
    fun ui(): CoroutineDispatcher = Dispatchers.Main
    fun io(): CoroutineDispatcher = Dispatchers.IO
}