package xyz.wayhua.kivy101.data.repository.impl

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers

import xyz.wayhua.kivy101.data.domain.Post
import xyz.wayhua.kivy101.data.repository.AppRepository
import xyz.wayhua.kivy101.data.repository.remote.IService
import xyz.wayhua.kivy101.data.repository.room.PostDao
import xyz.wayhua.kivy101.data.repository.room.PostEntity
import xyz.wayhua.kivy101.ext.coroutineAsync
import xyz.wayhua.kivy101.ext.default
import xyz.wayhua.kivy101.ext.sameContentWith


class AppRepositoryImpl(
    private val appWebDatasource: IService,
    private val postDao: PostDao
) : AppRepository {


    override fun getPostsAsync(): Deferred<List<Post>?> = coroutineAsync(Dispatchers.IO) {
        val local = localGetPostsAsync().await() ?: emptyList()
        val remote = remoteGetPostsAsync().await() ?: emptyList()

        if ((local sameContentWith remote).default) local
        else remote
    }

    private fun localGetPostsAsync(): Deferred<List<Post>?> = coroutineAsync(Dispatchers.IO) {
        postDao.getAllPosts().map {
            Post.from(it)
        }
    }

    private fun remoteGetPostsAsync(): Deferred<List<Post>?> = coroutineAsync(Dispatchers.IO) {
        val result = appWebDatasource.getPostsAsync().await()
        result.map {
            postDao.upsert(PostEntity.from(it))
            Post.from(it)
        }
    }

    override fun searchPostsAsync(query: String): Deferred<List<Post>?> = coroutineAsync(
        Dispatchers.IO
    ) {
        postDao.searchPosts(query).map {
            Post.from(it)
        }
    }
}