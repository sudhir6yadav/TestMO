package com.sd.testmo.data.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sd.testmo.data.entities.Item
import com.sd.testmo.databinding.ItemAdapterBinding

class ItemAdapter(private val listener: CharacterItemListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<Item>()

    fun setItems(items: ArrayList<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemAdapterBinding = ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])
}

class CharacterViewHolder(private val itemBinding: ItemAdapterBinding, private val listener: ItemAdapter.CharacterItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var item: Item

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Item) {
        this.item = item
        itemBinding.name.text = item.name
        itemBinding.speciesAndStatus.text = """${item.full_name} - ${item.statuses_url}"""
       /* Glide.with(itemBinding.root)
            .load(item.contributors_url)
            .transform(CircleCrop())
            .into(itemBinding.image)*/
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(item.id)
    }
}

