package com.fixedsolutions.fixedsolutionstask.ui.home.adapters

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fixedsolutions.fixedsolutionstask.MovieItem
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.common.getLoadingDrawable
import com.fixedsolutions.fixedsolutionstask.databinding.ItemMovieBinding
import com.fixedsolutions.fixedsolutionstask.databinding.ItemMovieShimmerBinding


class MoviesAdapter(var list: List<MovieItem?> = emptyList(), val onclick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MoviesViewHolder(private val rowView: ItemMovieBinding) :
        RecyclerView.ViewHolder(rowView.root) {
        fun onBind(item: MovieItem, position: Int) {
            rowView.apply {
                item.title?.let { title -> tvMovieName.text = title }
                item.imDbRating?.let { rating -> tvMovieRating.text = rating }
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

    inner class ShimmerViewHolder(private val rowView: ItemMovieShimmerBinding) :
        RecyclerView.ViewHolder(rowView.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE -> {
                MoviesViewHolder(
                    ItemMovieBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ShimmerViewHolder(
                    ItemMovieShimmerBinding.inflate(
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

    private fun setHighLightedText(tv: TextView, textToHighlight: String) {

        val wordToSpan: Spannable = SpannableString(tv.text)

        wordToSpan.setSpan(
            BackgroundColorSpan(-0x100),
            0,
            tv.text.indexOf(textToHighlight.last(), 0, true) + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv.setText(wordToSpan, TextView.BufferType.EDITABLE)
    }

    override fun getItemCount(): Int = list.size

    companion object {
        const val SHIMMER_TYPE = 1
        const val VIEW_TYPE = 2
    }

}

