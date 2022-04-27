package com.example.evotor.goods.ui.goods.goods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evotor.goods.Constants
import com.example.evotor.goods.R
import com.example.evotor.goods.databinding.SimpleListItemBinding
import com.example.evotor.goods.databinding.TileListItemBinding
import com.example.evotor.goods.entity.Good

class GoodsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val goods: MutableList<Good> = mutableListOf()
    var style: String = Constants.LIST_STYLE

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (style) {
            Constants.LIST_STYLE -> {
                val binding = SimpleListItemBinding.inflate(inflater, parent, false)
                GoodsViewHolder(binding)
            }
            else -> {
                val binding = TileListItemBinding.inflate(inflater, parent, false)
                GoodsGridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (style) {
            Constants.LIST_STYLE -> {
                val goodsViewHolder = holder as GoodsViewHolder
                configureListHolder(goodsViewHolder, position)
            }
            else -> {
                val goodsGridViewHolder = holder as GoodsGridViewHolder
                configureGridHolder(goodsGridViewHolder, position)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun configureGridHolder(holder: GoodsGridViewHolder, position: Int) {
        val good = goods[position]

        with(holder.binding) {
            goodNameTextview.text = good.name
            if (good.measureName == "шт") {
                quantityTextview.text = "${good.quantity.setScale(0)} ${good.measureName}"
            } else {
                quantityTextview.text = "${good.quantity} ${good.measureName}"
            }

            goodPriceTextview.text = "${good.price}" + " ₽."
        }
    }

    @SuppressLint("SetTextI18n")
    private fun configureListHolder(holder: GoodsViewHolder, position: Int) {
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
                quantityTextview.text = "${good.quantity.setScale(0)} ${good.measureName}"
            } else {
                quantityTextview.text = "${good.quantity} ${good.measureName}"
            }

            goodPriceTextview.text = "${good.price}" + " ₽."
        }
    }

    override fun getItemCount() = goods.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(goods: List<Good>) {
        this.goods.clear()
        this.goods.addAll(goods)
        notifyDataSetChanged()
    }

    class GoodsViewHolder(
        val binding: SimpleListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class GoodsGridViewHolder(
        val binding: TileListItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}