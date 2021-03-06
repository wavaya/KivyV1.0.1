package xyz.wayhua.kivy101.data.domain

import xyz.wayhua.kivy101.data.repository.remote.PostResponse
import xyz.wayhua.kivy101.data.repository.room.PostEntity


data class Post(
    val userId: Int = 0, // 10
    val id: Int = 0, // 100
    val title: String = "", // at nam consequatur ea labore ea harum
    val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) {
    companion object {
        fun from(post: PostResponse) = with(post) {
            Post(userId, id, title, body)
        }

        fun from(post: PostEntity) = with(post) {
            Post(userId, id, title, body)
        }
    }
}