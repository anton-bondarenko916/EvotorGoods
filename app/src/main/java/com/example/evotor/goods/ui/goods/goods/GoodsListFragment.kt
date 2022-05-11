package com.example.evotor.goods.ui.goods.goods

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evotor.goods.Constants
import com.example.evotor.goods.R
import com.example.evotor.goods.databinding.FragmentGoodsBinding
import com.example.evotor.goods.entity.Good
import com.example.evotor.goods.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoodsListFragment: Fragment() {

    private lateinit var  binding: FragmentGoodsBinding
    private val viewModel: GoodsListViewModel by viewModel()
    private lateinit var adapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = GoodsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoodsBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        viewModel.getGoods(Constants.SHOP_UUID)
        observeToGoods()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUpRecyclerView() {
        val style = viewModel.getStyle()
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
        adapter.style = style
        binding.goodsRecyclerview.layoutManager = layoutManager
        binding.goodsRecyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        setUpRecyclerView()
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