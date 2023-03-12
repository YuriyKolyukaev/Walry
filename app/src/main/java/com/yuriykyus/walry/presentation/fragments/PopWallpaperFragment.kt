package com.yuriykyus.walry.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yuriykyus.walry.core.AppConstants
import com.yuriykyus.walry.databinding.FragmentPhotoListBinding
import com.yuriykyus.walry.domain.models.PhotoData
import com.yuriykyus.walry.presentation.adapters.PhotoAdapter
import com.yuriykyus.walry.presentation.PhotoViewModel
import com.yuriykyus.walry.presentation.PhotoViewModelFactory
import com.yuriykyus.walry.presentation.events.LoadCityEvent
import com.yuriykyus.walry.presentation.events.LoadPhotoEvent
import com.yuriykyus.walry.presentation.states.PhotoState

class PopWallpaperFragment : BaseFragment() {
    companion object {
        fun newInstance() = PopWallpaperFragment()

        private const val API_TAG = "wallpaper"
        private const val API_TEXT = "popular"
    }

    private val binding by lazy {
        FragmentPhotoListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: PhotoAdapter

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            PhotoViewModelFactory(requireActivity())
        )[PhotoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        showLoad()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PhotoState -> {
                    adapter.addPhotoUrlList(state.photosUrl)
                    hideLoad()
                }
            }
        }

        viewModel.send(LoadCityEvent)

        val event = LoadPhotoEvent(photoData = PhotoData(API_TAG, API_TEXT))
        viewModel.send(event)
    }

    private fun initAdapter() {
        binding.apply {
            adapter = PhotoAdapter()
            rvPhoto.layoutManager = GridLayoutManager(context, 2)
            rvPhoto.adapter = adapter
            rvPhoto.setHasFixedSize(true)
        }
    }

    override fun getTitle(): String {
        return AppConstants.POP_WALLPAPER_FRAGMENT
    }


}