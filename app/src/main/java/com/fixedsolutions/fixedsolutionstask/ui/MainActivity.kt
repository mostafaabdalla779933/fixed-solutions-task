package com.fixedsolutions.fixedsolutionstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.common.DARK_MODE_KEY
import com.fixedsolutions.fixedsolutionstask.data.local.DataStoreManager
import com.fixedsolutions.fixedsolutionstask.databinding.ActivityMainBinding
import com.fixedsolutions.fixedsolutionstask.ui.setting.SettingState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        initDarkMode()
    }

    private fun initBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_host) as NavHostFragment).navController
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setOnItemReselectedListener { }
    }

    private fun initDarkMode(){
        lifecycleScope.launch {
            dataStoreManager.getInt(DARK_MODE_KEY, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM).onEach {
                AppCompatDelegate.setDefaultNightMode(it)
            }.catch {

            }.launchIn(lifecycleScope)
        }
    }

}