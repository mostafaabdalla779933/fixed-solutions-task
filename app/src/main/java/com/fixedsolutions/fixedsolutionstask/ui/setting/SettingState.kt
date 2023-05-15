package com.fixedsolutions.fixedsolutionstask.ui.setting

import com.fixedsolutions.fixedsolutionstask.data.model.ComplaintModel

data class SettingState(
    val darkModeOption: Int = -100,
    val list: MutableList<ComplaintModel> = mutableListOf()
)
