package com.yuriykyus.walry.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuriykyus.walry.core.AppConstants
import com.yuriykyus.walry.databinding.FragmentCategoriesBinding

class CategoriesFragment : BaseFragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    companion object {
        fun newInstance() = CategoriesFragment()
    }

    override fun getTitle(): String {
        return AppConstants.CATEGORY_FRAGMENT
    }


}