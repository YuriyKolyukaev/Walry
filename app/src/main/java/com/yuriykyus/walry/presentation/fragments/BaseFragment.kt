package com.yuriykyus.walry.presentation.fragments

import androidx.fragment.app.Fragment
import com.yuriykyus.walry.presentation.Navigator

abstract class BaseFragment : Fragment() {
    fun getNavigator(): Navigator {
        return (requireActivity() as Navigator)
    }

    fun hideLoad() {
        (requireActivity() as Navigator).hideLoad()
    }

    fun showLoad() {
        (requireActivity() as Navigator).showLoad()
    }
}