package com.pourkazemi.mahdi.maktab_hw_18_1.ui

import androidx.lifecycle.ViewModel
import com.pourkazemi.mahdi.maktab_hw_18_1.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

}