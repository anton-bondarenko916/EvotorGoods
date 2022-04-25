package com.example.evotor.goods.ui.goods.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.evotor.goods.Constants
import com.example.evotor.goods.R
import com.example.evotor.goods.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settings: SharedPreferences //TODO Добавить ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        settings = requireContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        initRadioButtons()
        setHasOptionsMenu(true)
        setUpRadioButtons()
        return binding.root
    }

    private fun setUpRadioButtons() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radio_list -> {
                    binding.imageviewStyle.setImageResource(R.drawable.preview_1)
                    settings.edit().putString(Constants.STYLE, Constants.LIST_STYLE).apply()
                }

                R.id.radio_tile -> {
                    binding.imageviewStyle.setImageResource(R.drawable.preview_2)
                    settings.edit().putString(Constants.STYLE, Constants.BIG_TILE_STYLE).apply()
                }

                R.id.radio_small_tile -> {
                    binding.imageviewStyle.setImageResource(R.drawable.preview_3)
                    settings.edit().putString(Constants.STYLE, Constants.SMALL_TILE_STYLE).apply()
                }
            }
        }
    }

    private fun initRadioButtons() {
        when(settings.getString(Constants.STYLE, Constants.LIST_STYLE)) {
            Constants.LIST_STYLE -> {
                binding.radioList.isChecked = true
                binding.imageviewStyle.setImageResource(R.drawable.preview_1)
            }

            Constants.BIG_TILE_STYLE -> {
                binding.radioTile.isChecked = true
                binding.imageviewStyle.setImageResource(R.drawable.preview_2)
            }

            Constants.SMALL_TILE_STYLE -> {
                binding.radioSmallTile.isChecked = true
                binding.imageviewStyle.setImageResource(R.drawable.preview_3)
            }
        }

    }
}