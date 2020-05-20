package xyz.wayhua.kivy101.mvvm.adapter

interface BaseBindableAdapter<in T> {
    fun setHeader(items: T) {}
    fun setData(items: List<T>) {}
    fun setFooter(items: T) {}
    fun bind(data: T) {}
}