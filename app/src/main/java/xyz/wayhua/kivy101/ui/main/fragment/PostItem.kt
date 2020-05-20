package xyz.wayhua.kivy101.ui.main.fragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import xyz.wayhua.kivy101.data.domain.Post
//import xyz.wayhua.kivy101.data.repository.room.PostEntity

@Parcelize
data class PostItem(
    val userId: Int = 0, // 10
    val id: Int = 0, // 100
    val title: String = "", // at nam consequatur ea labore ea harum
    val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) : Parcelable {
    companion object {
        fun from(model: Post) = with(model) {
            PostItem(userId, id, title, body)
        }

//        fun from(entity: PostEntity) = with(entity) {
//            PostItem(userId, id, title, body)
//        }
    }
}