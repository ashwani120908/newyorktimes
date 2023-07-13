package com.ashwani.nytimes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashwani.nytimes.R
import com.ashwani.nytimes.databinding.LayoutSectionedListHeaderBinding
import com.ashwani.nytimes.databinding.LayoutSectionedListItemBinding
import com.ashwani.nytimes.model.ListViewType
import com.ashwani.nytimes.model.SectionedList
import com.ashwani.nytimes.utils.Constants
import com.ashwani.nytimes.utils.Constants.LIST_TYPE_HEADER
import com.ashwani.nytimes.utils.Constants.LIST_TYPE_ITEM
import com.bumptech.glide.Glide

class SectionedListAdapter(private var context: Context, private var sectionedList: ArrayList<SectionedList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIST_TYPE_HEADER -> {
                val binding = LayoutSectionedListHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListTypeHeaderViewHolder(binding)
            }

            LIST_TYPE_ITEM -> {
                val binding = LayoutSectionedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListTypeItemViewHolder(binding)
            }

            else -> {
                val binding = LayoutSectionedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListTypeItemViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (sectionedList[position].viewType == ListViewType.HEADER) LIST_TYPE_HEADER else LIST_TYPE_ITEM
    }

    override fun getItemCount(): Int = sectionedList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val row = sectionedList[position]
        when (row.viewType) {
            ListViewType.HEADER -> (holder as ListTypeHeaderViewHolder).bind(sectionedList[position])
            ListViewType.ITEM -> (holder as ListTypeItemViewHolder).bind(sectionedList[position])
            else -> (holder as ListTypeItemViewHolder).bind(sectionedList[position])

        }
    }

    inner class ListTypeHeaderViewHolder(private val binding: LayoutSectionedListHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: SectionedList) {
            binding.tvHeader.text = getNameFromHeaderType(list.headerType)
        }

        private fun getNameFromHeaderType(postType: Constants.PostType?): String {
            return when (postType) {
                Constants.PostType.MOST_EMAILED -> context.getString(R.string.most_emailed)
                Constants.PostType.MOST_VIEWED -> context.getString(R.string.most_viewed)
                Constants.PostType.MOST_SHARED -> context.getString(R.string.most_shared)
                else -> context.getString(R.string.header)
            }
        }
    }

    inner class ListTypeItemViewHolder(private val binding: LayoutSectionedListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: SectionedList) {
            binding.tvItemTitle.text = list.result?.title
            binding.tvItemDesc.text = list.result?.abstract
            if (list.result?.media?.isNotEmpty() == true) {
                Glide
                    .with(context)
                    .load(list.result?.media?.get(0)?.mediaMetadata?.get(0)?.url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.ivItemImage)
            }
        }
    }
}