package com.fixedsolutions.fixedsolutionstask.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fixedsolutions.fixedsolutionstask.data.model.ComplaintModel
import com.fixedsolutions.fixedsolutionstask.databinding.ItemComplaintBinding


class ComplaintsAdapter() :
    ListAdapter<ComplaintModel, ComplaintsAdapter.AirLineViewHolder>(
        Callback
    ) {

    inner class AirLineViewHolder(private val rowView: ItemComplaintBinding) :
        RecyclerView.ViewHolder(rowView.root) {
        fun onBind(item: ComplaintModel, position: Int) {
            rowView.tvTitle.text = item.title
            rowView.tvDesc.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirLineViewHolder {
        return AirLineViewHolder(
            ItemComplaintBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AirLineViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }


    companion object {
        private val Callback = object : DiffUtil.ItemCallback<ComplaintModel>() {
            override fun areItemsTheSame(oldItem: ComplaintModel, newItem: ComplaintModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ComplaintModel,
                newItem: ComplaintModel
            ): Boolean =
                oldItem == newItem
        }
    }
}