package com.example.viewmodelhomework.viewmodel

import androidx.lifecycle.ViewModel

class TextDisplayViewModel : ViewModel() {

    private var selectedOption: Int = 0

    fun getSelectedOption(): Int {
        return selectedOption
    }

    fun setSelectedOption(option: Int) {
        selectedOption = option
    }
}
