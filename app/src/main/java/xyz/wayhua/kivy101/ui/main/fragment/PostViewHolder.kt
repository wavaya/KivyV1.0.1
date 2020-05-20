package xyz.wayhua.kivy101.ui.main.fragment

import androidx.recyclerview.widget.RecyclerView
import xyz.wayhua.kivy101.databinding.PostItemBinding
import xyz.wayhua.kivy101.mvvm.adapter.BaseBindableAdapter

class PostViewHolder(val binding: PostItemBinding) :
    RecyclerView.ViewHolder(binding.root),
    BaseBindableAdapter<PostItem> {

    override fun bind(data: PostItem) {
        binding.apply {
            item = data
            root.setOnClickListener {
//                val toPostDetail = PostFragmentDirections.toPostDetailAction(
//                    data
//                )
//
//                it.findNavController().navigate(toPostDetail)
            }
            executePendingBindings()
        }
    }
}