package xyz.wayhua.kivy101.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.wayhua.kivy101.data.repository.remote.PostResponse


@Entity(tableName = "tbl_post")
data class PostEntity(
    @PrimaryKey
    val id: Int = 0, // 1
    val userId: Int = 0, // 10
    val title: String = "", // at nam consequatur ea labore ea harum
    val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) {
    companion object {
        fun from(response: PostResponse) = with(response) {
            PostEntity(id, userId, title, body)
        }
    }
}