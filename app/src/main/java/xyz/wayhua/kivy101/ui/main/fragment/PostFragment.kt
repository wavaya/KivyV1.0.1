package xyz.wayhua.kivy101.ui.main.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.consumeEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException
import xyz.wayhua.kivy101.R
import xyz.wayhua.kivy101.data.domain.Post
import xyz.wayhua.kivy101.databinding.PostFragmentBinding
import xyz.wayhua.kivy101.ext.coroutineLaunch
import xyz.wayhua.kivy101.list.EndlessRecyclerOnScrollListener
import xyz.wayhua.kivy101.mvvm.ErrorState
import xyz.wayhua.kivy101.mvvm.LoadingState
//import xyz.wayhua.kivy101.mvvm.PostListState
import xyz.wayhua.kivy101.mvvm.SuccessState
import xyz.wayhua.kivy101.ui.base.BaseUserActionListener
import xyz.wayhua.kivy101.ui.base.ToolbarListener

class PostFragment : Fragment(), BaseUserActionListener {
    private lateinit var viewBinding: PostFragmentBinding
    private val viewModel by viewModel<PostViewModel>()
    private lateinit var onLoadMore: EndlessRecyclerOnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = PostFragmentBinding.inflate(inflater, container, false)
            .apply {
                listener = this@PostFragment
                vm = viewModel
            }
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (::onLoadMore.isInitialized) {
            onLoadMore.resetState()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as ToolbarListener).updateTitleToolbar(newTitle = getString(R.string.app_name))
        setupObserver()
        if (savedInstanceState == null) {
            setupData()
        }
    }

    override fun onRefresh() {
        Handler().postDelayed({
            pullToRefresh()
        }, 1000)
    }

    private fun setupData() {
        viewModel.apply {
            getPosts()
        }
    }

    private fun setupObserver() = with(viewModel) {
        // Observe ComposeState
        states.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (state) {
                    is LoadingState -> showIsLoading()
                    is SuccessState<*>  -> {
                        showCategoryItemList(
                            posts = state.data.map { PostItem.from(it as  Post ) })
                    }
                    is ErrorState -> showError(state.error)
                }
            }
        })

        coroutineLaunch(Main) {
            keywords.consumeEach { searchPosts(it) }
        }
    }

    private fun pullToRefresh() {
        viewModel.apply {
            isRefreshing.set(true)
            if (::onLoadMore.isInitialized) onLoadMore.resetState()
            getPosts()
        }
    }

    private fun showIsLoading() = with(viewModel) {
        isError.set(false)
        progress.set(true)
        isRefreshing.set(false)
    }

    // TODO: Fix this bloated converter err handle
    private fun showError(err: Throwable) = with(viewBinding) {
        viewModel.apply {
            isError.set(true)
            progress.set(false)
            isRefreshing.set(false)

            val humanizedMsg = when (err) {
                is HttpException -> {
                    when (err.code()) {
                        403 -> "Try again later after a minute, you've exceeded the rate limit!"
                        422 -> "You have to type meaningful character, e.g: john doe"
                        //    else -> converter<Any>(err)?.message
                        else -> "else error"
                    }
                }
                else -> {
                    err.localizedMessage
                }
            }

            errMsg.set(humanizedMsg)

            error.btnRetry.setOnClickListener {
                if (::onLoadMore.isInitialized) onLoadMore.resetState()
                getPosts()
            }
        }
    }

    private fun showCategoryItemList(posts: List<PostItem>) {
        viewModel.apply {
            // TODO: @mochadwi clearing list doesn't good for pagination?
            postListSet.clear()
            postListSet.addAll(posts.toMutableList())
            isRefreshing.set(false)
            progress.set(false)
            isError.set(false)
        }
    }

//    // TODO: Move this into utils class
//    private inline fun <reified T : Any> converter(error: HttpException): BaseApiModel<T>? {
//        var baseDao: BaseApiModel<T>? = null
//        try {
//            val body = error.response()!!.errorBody()
////            baseDao = body?.string()?.fromJson(BaseApiModel.serializer(T::class.serializer()))
//        } catch (exception: Exception) {
//
//        }
//
//        return baseDao
//    }
}