package xyz.wayhua.kivy101.mvvm

import xyz.wayhua.kivy101.data.domain.Post

/**
 * Abstract State
 */
sealed class State

/**
 * Generic Loading State
 */
object LoadingState : State()

/**
 * Generic Error state
 * @param error - caught error
 */
data class ErrorState(val error: Throwable) : State()

//data class PostListState(
//    val list: List<Post>
//) : State() {
//    companion object {
//        fun from(list: List<Post>): PostListState {
//            return with(list) {
//                when {
//                    // TODO: @mochadwi Move this into strings instead
//                    isEmpty() -> error("There's an empty post instead, please check your keyword")
//                    else -> PostListState(this)
//                }
//            }
//        }
//    }
//}

data class SuccessState<T>(val data: List<T>) : State() {
    companion object {
        fun <T> from(data: List<T>): SuccessState<T> {
            return with(data) {
                when {
                    isEmpty() -> error("不能为空")
                    else -> SuccessState(this)
                }
            }
        }
    }
}