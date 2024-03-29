package com.yuriykyus.walry.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yuriykyus.walry.app.AppConst
import com.yuriykyus.walry.presentation.fragments.*

class ViewPagerAdapter(fragment: FragmentManager, lifeCircle: androidx.lifecycle.Lifecycle) :
    FragmentStateAdapter(fragment, lifeCircle) {

    private val categoriesFragment = CategoriesFragment.newInstance()
    private val cityFragment = WallpaperFragment.newInstance(FragmentTypes.CityPhotos)
    private val newWallpaper = WallpaperFragment.newInstance(FragmentTypes.NewWallpaper)
    private val popWallpaper = WallpaperFragment.newInstance(FragmentTypes.PopWallpaper)
    private val illustration = WallpaperFragment.newInstance(FragmentTypes.Illustration)

    private val fragments = arrayOf(
        categoriesFragment,
        cityFragment,
        newWallpaper,
        popWallpaper,
        illustration
    )

    val fragmentName =
        arrayOf(
            AppConst.CATEGORY_FRAGMENT_TITLE,
            FragmentTypes.CityPhotos.title,
            FragmentTypes.NewWallpaper.title,
            FragmentTypes.PopWallpaper.title,
            FragmentTypes.Illustration.title
        )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}