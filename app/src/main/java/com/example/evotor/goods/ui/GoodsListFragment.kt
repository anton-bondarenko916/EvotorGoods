package com.example.evotor.goods.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evotor.goods.Constants
import com.example.evotor.goods.databinding.FrgmentGoodsBinding
import com.example.evotor.goods.entity.Good
import com.example.evotor.goods.entity.Items
import com.example.evotor.goods.repository.Repository
import com.example.evotor.goods.utils.Status


class GoodsListFragment: Fragment() {

    private lateinit var binding: FrgmentGoodsBinding
    private lateinit var repository: Repository
    private lateinit var viewModel: GoodsListViewModel

    //TODO Инициализация происходит позже создания фрагмента (иначе краш)
    private val adapter by lazy { GoodsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository()
        viewModel = ViewModelProvider(this, object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GoodsListViewModel(repository) as T
            }
        })[GoodsListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrgmentGoodsBinding.inflate(inflater, container, false)
        viewModel.getGoods(Constants.SHOP_UUID, Constants.TOKEN)
        observeToGoods()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.goodsRecyclerview.adapter = adapter
        binding.goodsRecyclerview.layoutManager = layoutManager
    }

    private fun observeToGoods() {
        //TODO Обрабатываем события, происходящие с нашим запросов во viewmodel
        viewModel.observeGoodsResponse().observe(viewLifecycleOwner) { event ->
            when(event.status) {
                Status.LOADING -> {
                    binding.loadGoodsProgressbar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.loadGoodsProgressbar.visibility = View.GONE
                    adapter.addAll((event.data as Items).items)
                }
                Status.ERROR -> {
                    binding.loadGoodsProgressbar.visibility = View.GONE
                    Toast.makeText(context, event.throwable?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
//        viewModel.goodsResponse.observe(viewLifecycleOwner) { goodsResponse ->
//                if(goodsResponse.isSuccessful) {
//                    binding.loadGoodsProgressbar.visibility = View.GONE
//                    goodsResponse.body()?.items?.let { initRecyclerView(it) }
//                } else {
//                    //TODO: add read from database
//                }
//        }
    }
}