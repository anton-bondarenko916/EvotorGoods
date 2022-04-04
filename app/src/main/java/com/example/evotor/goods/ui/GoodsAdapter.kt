package com.example.evotor.goods.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evotor.goods.Constants
import com.example.evotor.goods.R
import com.example.evotor.goods.databinding.SimpleListItemBinding
import com.example.evotor.goods.entity.Good

class GoodsAdapter : RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder>() {

    private val goods: MutableList<Good> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GoodsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleListItemBinding.inflate(inflater, parent, false)
        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        val good = goods[position]

        with(holder.binding) {
            goodNameTextview.text = good.name
            if (!good.allowToSell) {
                deprecatedSaleTextview.visibility = View.VISIBLE
                deprecatedSaleTextview.text = deprecatedSaleTextview.context.getString(R.string.sale_deprecated)
            } else {
                goodNameTextview.textSize = Constants.TEXT_SIZE
            }

            if (good.measureName == "шт") {
                quantityTextview.text = "${good.quantity.toInt()}${good.measureName}"
            } else {
                quantityTextview.text = "${good.quantity}${good.measureName}"
            }

            goodPriceTextview.text = "${good.price} P."
        }

    }

    override fun getItemCount() = goods.size

    fun addAll(goods: List<Good>) {
        this.goods.clear()
        this.goods.addAll(goods)
        notifyDataSetChanged()
    }

    class GoodsViewHolder(
        val binding: SimpleListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}