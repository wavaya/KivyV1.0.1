package xyz.wayhua.kivy101.ext

import xyz.wayhua.kivy101.data.domain.Post


/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 12/05/19 for social-app
 */

val Boolean?.default: Boolean
    get() = this ?: false

val Int?.default: Int
    get() = this ?: 0

val Double?.default: Double
    get() = this ?: 0.0

val Float?.default: Float
    get() = this ?: 0F

val String?.default: String
    get() = this ?: ""

val <T> ArrayList<T>?.default: ArrayList<T>
    get() = this ?: arrayListOf()

val Post?.default: Post
    get() = this ?: Post()