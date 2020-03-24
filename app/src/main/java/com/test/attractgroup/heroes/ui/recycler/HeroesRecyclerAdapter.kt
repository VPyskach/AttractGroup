package com.test.attractgroup.heroes.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.attractgroup.R
import com.test.attractgroup.common.getDateFormat
import com.test.attractgroup.common.loadImage
import com.test.attractgroup.heroes.data.model.Hero
import kotlinx.android.synthetic.main.hero_item_recycler_layout.view.*

class HeroesRecyclerAdapter(
    private val listener: HeroItemClickListener
) : RecyclerView.Adapter<HeroesRecyclerAdapter.HeroesRecyclerViewHolder>() {


    private val list = ArrayList<Hero>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesRecyclerViewHolder {
        return HeroesRecyclerViewHolder.instance(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HeroesRecyclerViewHolder, position: Int) {
        holder.bindView(list[position], listener)
    }

    fun setData(list: List<Hero>){
        this.list.clear()
        this.list.addAll(list)
    }

    fun updateData(list: List<Hero>) {
        setData(list)
        this.notifyDataSetChanged()
    }

    class HeroesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun instance(parent: ViewGroup): HeroesRecyclerViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.hero_item_recycler_layout, parent, false)
                return HeroesRecyclerViewHolder(view)
            }
        }

        fun bindView(data: Hero, listener: HeroItemClickListener) {
            itemView.ivHeroItemIcon.loadImage(itemView.context, data.image)
            itemView.tvHeroItemName.text = data.name
            itemView.tvHeroItemDate.text = data.time.getDateFormat()
            itemView.clHeroItem.setOnClickListener { listener.onHeroClick(data.id) }
        }
    }

}