package com.ridhoafni.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ridhoafni.core.BuildConfig
import com.ridhoafni.core.R
import com.ridhoafni.core.databinding.ItemMovieBinding
import com.ridhoafni.core.domain.model.Movie
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>(){

    private var movieList = ArrayList<Movie>()

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        movieList.clear()
        movieList.addAll(newListData)
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Movie){
            with(binding){
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_ENDPOINT+data.posterPath)
                    .placeholder(R.drawable.ic_loading).error(R.drawable.ic_error)
                    .into(ivPoster)
                tvMovieTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvOverview.text = data.overview
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }

}