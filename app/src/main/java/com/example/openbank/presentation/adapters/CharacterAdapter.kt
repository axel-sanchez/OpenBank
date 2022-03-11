package com.example.openbank.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.openbank.R
import com.example.openbank.data.model.CharacterDTO

/**
 * @author Axel Sanchez
 */
class CharacterAdapter(
    private val characterList: List<CharacterDTO?>,
    private val itemClick: (CharacterDTO?) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characterList[position]

        with(holder){
            tvName.text = item?.name

            Glide
                .with(itemView)
                .load(item?.thumbnail.toString())
                .centerCrop()
                .into(ivCoverPage)

            itemView.setOnClickListener { itemClick(item) }
        }
    }

    override fun getItemCount(): Int = characterList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val ivCoverPage: ImageView = view.findViewById(R.id.ivCoverPage)
    }
}