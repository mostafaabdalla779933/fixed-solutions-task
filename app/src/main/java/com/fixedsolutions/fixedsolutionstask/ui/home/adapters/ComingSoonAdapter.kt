package com.fixedsolutions.fixedsolutionstask.ui.home.adapters

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fixedsolutions.fixedsolutionstask.MovieItem
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.common.getLoadingDrawable
import com.fixedsolutions.fixedsolutionstask.databinding.ItemMovieBinding



class ComingSoonAdapter(var list: List<MovieItem?> = emptyList(), val onclick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder>() {

    inner class ComingSoonViewHolder(private val rowView: ItemMovieBinding) :
        RecyclerView.ViewHolder(rowView.root) {
        fun onBind(item: MovieItem, position: Int) {
            rowView.apply {
                item.title?.let { title -> tvMovieName.text = title }
                item.imDbRating?.let { rating -> tvMovieRating.text = rating }
                Glide.with(itemView.context)
                    .load(item.image)
                    .placeholder(itemView.context.getLoadingDrawable())
                    .error(itemView.context.getDrawable(R.drawable.ic_error_loading))
                    .into(ivMovie)
                root.setOnClickListener {
                    onclick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonViewHolder {
        return ComingSoonViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        list[position]?.let { holder.onBind(it, position) }
    }

    fun setMovieList(list: List<MovieItem?>){
        this.list = list
        notifyDataSetChanged()
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

    override fun getItemCount(): Int  = list.size

}

