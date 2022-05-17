package com.pourkazemi.mahdi.maktab_hw_18_1.ui

import androidx.fragment.app.Fragment
import com.pourkazemi.mahdi.maktab_hw_18_1.R
import com.pourkazemi.mahdi.maktab_hw_18_1.databinding.FragmentDatabaseListBinding
import com.pourkazemi.mahdi.maktab_hw_18_1.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DatabaseListFragment : Fragment(R.layout.fragment_database_list) {
    private val binding: FragmentDatabaseListBinding by viewBinding(FragmentDatabaseListBinding::bind)


}