package com.kodo.friple.mvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kodo.friple.mvvm.model.models.RegModel

class RegViewModel: ViewModel() {
    var repoModel: RegModel = RegModel()

    val text = ObservableField<String>()

    val isLoading = ObservableField<Boolean>()

    fun refresh(){
        isLoading.set(true)
        repoModel.refreshData(object : RegModel.OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }
}