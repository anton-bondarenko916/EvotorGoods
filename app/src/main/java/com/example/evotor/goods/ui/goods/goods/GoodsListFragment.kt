package com.example.evotor.goods.ui.goods.goods

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evotor.goods.Constants
import com.example.evotor.goods.R
import com.example.evotor.goods.databinding.FragmentGoodsBinding
import com.example.evotor.goods.entity.Good
import com.example.evotor.goods.utils.Status


class GoodsListFragment: Fragment() {

    private lateinit var binding: FragmentGoodsBinding
    private lateinit var viewModel: GoodsListViewModel
    private lateinit var settings: SharedPreferences
    private lateinit var adapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[GoodsListViewModel::class.java]
        adapter = GoodsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoodsBinding.inflate(inflater, container, false)
        settings = requireContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        setUpRecyclerView()
        Log.d("MYTAG", "onCreateView")
        viewModel.getGoods(Constants.SHOP_UUID)
        observeToGoods()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUpRecyclerView() {
        val style = settings.getString(Constants.STYLE, Constants.LIST_STYLE)
        val layoutManager: RecyclerView.LayoutManager = when(style) {
            Constants.BIG_TILE_STYLE -> {
                GridLayoutManager(context, 2)
            }
            Constants.SMALL_TILE_STYLE -> {
                GridLayoutManager(context, 3)
            }
            else -> {
                LinearLayoutManager(context)
            }
        }
        adapter.style = style ?: Constants.LIST_STYLE
        binding.goodsRecyclerview.layoutManager = layoutManager
        binding.goodsRecyclerview.adapter = adapter
        Log.d("MYTAG", "setUp")
    }

    override fun onStart() {
        super.onStart()
        setUpRecyclerView()
        Log.d("MYTAG", "onStart")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MYTAG", "onViewCreated")
    }

    private fun observeToGoods() {
        viewModel.observeGoodsResponse().observe(viewLifecycleOwner) { event ->
            when(event.status) {
                Status.LOADING -> {
                    binding.loadGoodsProgressbar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.loadGoodsProgressbar.visibility = View.GONE
                    adapter.addAll(event.data as List<Good>)
                }
                Status.ERROR -> {
                    binding.loadGoodsProgressbar.visibility = View.GONE
                    Toast.makeText(context, event.throwable?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings_menu) {
            findNavController().navigate(R.id.action_goodsListFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}