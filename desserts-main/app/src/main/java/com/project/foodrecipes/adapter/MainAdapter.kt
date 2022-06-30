package com.project.foodrecipes.adapter

import com.project.foodrecipes.model.ModelMain
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.project.foodrecipes.R
import com.squareup.picasso.Picasso

class MainAdapter(private val items: List<ModelMain>, private var listener: OnSelectData) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface OnSelectData {
        fun onSelected(modelMain: ModelMain?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_dessert, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        val picasso : Picasso = Picasso.get()
        picasso.load(data.strMealThumb)
            .placeholder(R.drawable.ic_food_placeholder)
            .into(holder.imageCategories)
        holder.textViewCategories.text = data.strMeal
        holder.cardViewCategories.setOnClickListener { listener.onSelected(data) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewCategories: TextView
        var cardViewCategories: CardView
        var imageCategories: ImageView

        init {
            cardViewCategories = itemView.findViewById(R.id.categories)
            textViewCategories = itemView.findViewById(R.id.textViewCategories)
            imageCategories = itemView.findViewById(R.id.imageCategory)
        }
    }
}