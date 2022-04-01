package com.example.evotor.goods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.evotor.goods.databinding.ActivityMainBinding
import com.example.evotor.goods.ui.GoodsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, GoodsListFragment())
                .commit()
        }
        updateToolBar()
    }

    private fun updateToolBar() {
        binding.toolbar.title = getString(R.string.goods)

        binding.toolbar.menu.clear()
        val iconDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, R.drawable.settings_button)!!)

        val menuItem = binding.toolbar.menu.add("Menu")
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
//        menuItem.setOnMenuItemClickListener {
//            action.onCustomAction.run()
//            return@setOnMenuItemClickListener true
//        }
    }
}