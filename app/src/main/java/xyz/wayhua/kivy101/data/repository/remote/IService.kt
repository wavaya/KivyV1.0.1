package xyz.wayhua.kivy101.data.repository.remote


import kotlinx.coroutines.Deferred
import retrofit2.http.GET


/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/05/19
 * dedicated to build social-app
 *
 */

interface IService {
    @GET("posts")
    fun getPostsAsync(): Deferred<List<PostResponse>>
}