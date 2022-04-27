package com.example.evotor.goods.ui.goods.settings


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.evotor.goods.Constants
import com.example.evotor.goods.R
import com.example.evotor.goods.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpRadioButtons()
        initRadioButtons()
        return binding.root
    }

    private fun setUpRadioButtons() {
        binding.listButton.setOnClickListener { onListButtonClick() }
        binding.listRadiobutton.setOnClickListener { onListButtonClick() }

        binding.bigTileButton.setOnClickListener { onBigTileButtonClick() }
        binding.bigTileRadiobutton.setOnClickListener { onBigTileButtonClick() }

        binding.smallTileButton.setOnClickListener { onSmallTileButtonClick() }
        binding.smallTileRadiobutton.setOnClickListener { onSmallTileButtonClick() }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun activateButton(
        innerLayout: ConstraintLayout,
        textView: TextView,
        radioButton: RadioButton) {
        innerLayout.background  = requireContext().getDrawable(R.drawable.radio_selected)
        textView.setTextColor(requireContext().getColor(R.color.green_custom))
        radioButton.isChecked = true
        disableOtherButtons(innerLayout.id)
    }

    private fun disableOtherButtons(id: Int) {
        when (id) {
            R.id.list_button_inner -> {
                disableButton(binding.bigTileButtonInner, binding.bigTileTextview, binding.bigTileRadiobutton)
                disableButton(binding.smallTileButtonInner, binding.smallTileTextview, binding.smallTileRadiobutton)
            }
            R.id.big_tile_button_inner -> {
                disableButton(binding.listButtonInner, binding.listTextview, binding.listRadiobutton)
                disableButton(binding.smallTileButtonInner, binding.smallTileTextview, binding.smallTileRadiobutton)
            }
            R.id.small_tile_button_inner -> {
                disableButton(binding.listButtonInner, binding.listTextview, binding.listRadiobutton)
                disableButton(binding.bigTileButtonInner, binding.bigTileTextview, binding.bigTileRadiobutton)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun disableButton(
        innerLayout: ConstraintLayout,
        textView: TextView,
        radioButton: RadioButton) {
        innerLayout.background = requireContext().getDrawable(R.drawable.radio_normal)
        textView.setTextColor(requireContext().getColor(R.color.white))
        radioButton.isChecked = false
    }

    private fun onListButtonClick() {
        activateButton(binding.listButtonInner, binding.listTextview, binding.listRadiobutton)
        viewModel.saveStyle(Constants.LIST_STYLE)
        binding.imageviewStyle.setImageResource(R.drawable.preview_1)
    }

    private fun onBigTileButtonClick() {
        activateButton(binding.bigTileButtonInner, binding.bigTileTextview, binding.bigTileRadiobutton)
        viewModel.saveStyle(Constants.BIG_TILE_STYLE)
        binding.imageviewStyle.setImageResource(R.drawable.preview_2)
    }

    private fun onSmallTileButtonClick() {
        activateButton(binding.smallTileButtonInner, binding.smallTileTextview, binding.smallTileRadiobutton)
        viewModel.saveStyle(Constants.SMALL_TILE_STYLE)
        binding.imageviewStyle.setImageResource(R.drawable.preview_3)
    }

    private fun initRadioButtons() {
        when(viewModel.getStyle()) {
            Constants.LIST_STYLE -> {
                activateButton(binding.listButtonInner, binding.listTextview, binding.listRadiobutton)
                binding.imageviewStyle.setImageResource(R.drawable.preview_1)
            }

            Constants.BIG_TILE_STYLE -> {
                activateButton(binding.bigTileButtonInner, binding.bigTileTextview, binding.bigTileRadiobutton)
                binding.imageviewStyle.setImageResource(R.drawable.preview_2)
            }

            Constants.SMALL_TILE_STYLE -> {
                activateButton(binding.smallTileButtonInner, binding.smallTileTextview, binding.smallTileRadiobutton)
                binding.imageviewStyle.setImageResource(R.drawable.preview_3)
            }
        }
    }
}