package com.yuriykyus.walry.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuriykyus.walry.core.App
import com.yuriykyus.walry.data.repository.CityRepositoryImpl
import com.yuriykyus.walry.data.repository.PhotoRepositoryImpl
import com.yuriykyus.walry.data.storage.sharedprefs.CitySharedPrefs
import com.yuriykyus.walry.domain.usecase.GetCityNameUseCase
import com.yuriykyus.walry.domain.usecase.GetCityPhotoUseCase

class PhotoViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val photoRepository by lazy {
        PhotoRepositoryImpl(App.networkManager)
    }

    private val cityRepository by lazy {
        CityRepositoryImpl(cityStorageImpl = CitySharedPrefs(context = context))
    }

    private val getCityNameUseCase by lazy {
        GetCityNameUseCase(cityRepository = cityRepository)
    }

    private val getCityPhotoUseCase by lazy {
        GetCityPhotoUseCase(photoRepository = photoRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoViewModel(
            getCityNameUseCase = getCityNameUseCase,
            getCityPhotoUseCase = getCityPhotoUseCase
        ) as T
    }

}