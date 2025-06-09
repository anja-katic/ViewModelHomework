package com.example.yourapp.viewmodel  // prilagodi prema svom paketu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(
    private val gameName: String,
    private val result: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(gameName, result) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


