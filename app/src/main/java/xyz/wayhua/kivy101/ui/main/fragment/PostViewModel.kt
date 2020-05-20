package xyz.wayhua.kivy101.ui.main.fragment

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

import xyz.wayhua.kivy101.data.repository.AppRepository
import xyz.wayhua.kivy101.ext.toSingleEvent
import xyz.wayhua.kivy101.mvvm.ErrorState
import xyz.wayhua.kivy101.mvvm.LoadingState
//import xyz.wayhua.kivy101.mvvm.PostListState
import xyz.wayhua.kivy101.mvvm.State
import xyz.wayhua.kivy101.mvvm.SuccessState
import xyz.wayhua.kivy101.mvvm.live.LiveEvent
import xyz.wayhua.kivy101.mvvm.live.MutableSetObservableField
import xyz.wayhua.kivy101.mvvm.viewmodel.BaseViewModel
import xyz.wayhua.kivy101.rx.SchedulerProvider

class PostViewModel(
    private val appRepository: AppRepository,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider){
    val keywords = Channel<String>(Channel.UNLIMITED)
    var postListSet = MutableSetObservableField<PostItem>()
    /*
         * We use LiveEvent to publish "states"
         * No need to publish and retain any view state
         */
    private val _states = LiveEvent<State>()
    val states: LiveData<State>
        get() = _states.toSingleEvent()

    fun getPosts() {
        _states.value = LoadingState

        launch {
            try {
                val posts = appRepository.getPostsAsync().await()

                _states.value = SuccessState.from(posts!!)
            } catch (error: Throwable) {
                _states.value = ErrorState(error)
            }
        }
    }


    fun searchPosts(query: String) {
        if (query.isNotBlank()) {
            _states.value = LoadingState

            launch {
                try {
                    val posts = appRepository.searchPostsAsync(query).await()

                    _states.value = SuccessState.from(posts!!)
                } catch (error: Throwable) {
                    _states.value = ErrorState(error)
                }
            }
        }
    }

}