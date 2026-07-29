package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView.models.RVItem
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView.utils.Constants

class RVAdapter(val context: Context, val elements: MutableList<RVItem>) :
    RecyclerView.Adapter<RVAdapter.RVViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RVViewHolder,
        position: Int
    ) {
        val currentItem = elements[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    inner class RVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_title)
        val description: TextView = view.findViewById(R.id.tv_description)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val item = elements[position]
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra(Constants.KEY_TITLE, item.title)
                intent.putExtra(Constants.KEY_DESCRIPTION, item.description)

                context.startActivity(intent)
            }
        }

    }


}