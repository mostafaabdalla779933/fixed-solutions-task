package com.fixedsolutions.fixedsolutionstask.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fixedsolutions.fixedsolutionstask.MovieItem
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.common.getLoadingDrawable
import com.fixedsolutions.fixedsolutionstask.databinding.ItemMovieVerticalBinding
import com.fixedsolutions.fixedsolutionstask.databinding.ItemMovieVerticalShimmerBinding


class MoviesVerticalAdapter(var list: List<MovieItem?> = emptyList(), val onclick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MoviesViewHolder(private val rowView: ItemMovieVerticalBinding) :
        RecyclerView.ViewHolder(rowView.root) {
        fun onBind(item: MovieItem, position: Int) {
            rowView.apply {
                item.title?.let { title -> tvMovieName.text = title }
                item.imDbRating?.let { rating ->
                    tvMovieRating.visibility = View.VISIBLE
                    tvMovieRating.text = rating
                } ?: kotlin.run {
                    tvMovieRating.visibility = View.INVISIBLE
                }
                Glide.with(itemView.context)
                    .load(item.image)
                    .placeholder(itemView.context.getLoadingDrawable())
                    .error(
                        AppCompatResources.getDrawable(
                            itemView.context,
                            R.drawable.ic_error_loading
                        )
                    )
                    .into(ivMovie)
                root.setOnClickListener {
                    onclick(item)
                }
            }
        }
    }

    inner class ShimmerViewHolder(private val rowView: ItemMovieVerticalShimmerBinding) :
        RecyclerView.ViewHolder(rowView.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE -> {
                MoviesViewHolder(
                    ItemMovieVerticalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ShimmerViewHolder(
                    ItemMovieVerticalShimmerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesViewHolder -> {
                list[position]?.let { holder.onBind(it, position) }
            }
            is ShimmerViewHolder -> {

            }
        }
    }

    fun setMovieList(list: List<MovieItem?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.getOrNull(position)?.isShimmer == true) {
            SHIMMER_TYPE
        } else {
            VIEW_TYPE
        }
    }

    override fun getItemCount(): Int = list.size

    companion object {
        const val SHIMMER_TYPE = 1
        const val VIEW_TYPE = 2
    }

}