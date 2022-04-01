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

class GoodsAdapter(private val goods: ArrayList<Good>): RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GoodsViewHolder {
        context = parent.context
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
                deprecatedSaleTextview.text = context.getString(R.string.sale_deprecated)
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

    class GoodsViewHolder(
        val binding: SimpleListItemBinding
    ): RecyclerView.ViewHolder(binding.root)

}