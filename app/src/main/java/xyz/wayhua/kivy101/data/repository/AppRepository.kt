package xyz.wayhua.kivy101.data.repository

import kotlinx.coroutines.Deferred
import xyz.wayhua.kivy101.data.domain.Post


interface AppRepository {
    fun getPostsAsync(): Deferred<List<Post>?>
    fun searchPostsAsync(query: String): Deferred<List<Post>?>
}

