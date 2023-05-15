package com.fixedsolutions.fixedsolutionstask.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private lateinit var viewModel: SettingVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SettingVM::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getDarkModeOption()
        lifecycleScope.launch {
            viewModel.state.collect {
                handleState(it)
            }
        }

    }

    private fun initView() {
        binding.rgDarkMode.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbFollow -> {
                    viewModel.setDarkModeOption(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                R.id.rbDark -> {
                    viewModel.setDarkModeOption(AppCompatDelegate.MODE_NIGHT_YES)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

                R.id.rbLight -> {
                    viewModel.setDarkModeOption(AppCompatDelegate.MODE_NIGHT_NO)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    private fun handleState(state: SettingState) {
        binding.apply {
            when (state.darkModeOption) {
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                    rbFollow.isChecked = true
                }
                AppCompatDelegate.MODE_NIGHT_NO -> {
                    rbLight.isChecked = true
                }
                AppCompatDelegate.MODE_NIGHT_YES -> {
                    rbDark.isChecked = true
                }
            }
        }
    }
}