package com.example.dorstip_app.dashboard.Categories

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dorstip_app.R
import com.example.dorstip_app.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPostion = -1
    class Viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.tvCategory.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.ivCategory)

        holder.binding.root.setOnClickListener{
            lastSelectedPostion=selectedPosition
            selectedPosition=position
            notifyItemChanged(selectedPosition)
            notifyItemChanged(lastSelectedPostion)
        }

        holder.binding.tvCategory.setTextColor(context.resources.getColor(R.color.accent))
        if (selectedPosition==position){
            holder.binding.layoutCategory.setBackgroundResource(R.drawable.category_background)
            ImageViewCompat.setImageTintList(holder.binding.ivCategory, ColorStateList.valueOf(context.getColor(R.color.primary)))

            holder.binding.tvCategory.visibility=View.VISIBLE
    }
}
    override fun getItemCount(): Int = items.size
    }

