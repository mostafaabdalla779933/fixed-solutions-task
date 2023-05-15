package com.fixedsolutions.fixedsolutionstask.ui.setting

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fixedsolutions.fixedsolutionstask.common.DARK_MODE_KEY
import com.fixedsolutions.fixedsolutionstask.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingVM @Inject constructor(val dataStoreManager: DataStoreManager): ViewModel() {


    val state = MutableStateFlow(SettingState(-100))

    fun getDarkModeOption() {
        viewModelScope.launch {
            dataStoreManager.getInt(DARK_MODE_KEY, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM).onEach {
                state.value = SettingState(it)
            }.catch {

            }.launchIn(viewModelScope)
        }
    }


    fun setDarkModeOption(option:Int){
        viewModelScope.launch {
            dataStoreManager.setInt(DARK_MODE_KEY,option)
        }

    }



}