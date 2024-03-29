package com.yuriykyus.walry.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriykyus.walry.app.*
import com.yuriykyus.walry.app.toMutable
import com.yuriykyus.walry.domain.models.PhotoData
import com.yuriykyus.walry.domain.models.PhotoUrl
import com.yuriykyus.walry.domain.models.SearchData
import com.yuriykyus.walry.domain.usecase.GetCityNameUseCase
import com.yuriykyus.walry.domain.usecase.GetPhotosUseCase
import com.yuriykyus.walry.presentation.events.LoadPhotoSearchEvent
import com.yuriykyus.walry.presentation.events.MainEvent
import com.yuriykyus.walry.presentation.fragments.FragmentTypes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoViewModel(
    private val getCityNameUseCase: GetCityNameUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
) : ViewModel() {

    val listPhotosLiveData: LiveData<ViewState<List<PhotoUrl>>> = MutableLiveData()

    fun send(event: MainEvent) {
        when (event.type) {
            FragmentTypes.CityPhotos -> getCity()
            FragmentTypes.SearchPhoto -> {
                event as LoadPhotoSearchEvent
                getSearchPhotos(event.searchData)
            }

            else -> getPhotoList(photoData = PhotoData(event.type?.requestTag ?: "", event.type?.requestText ?: ""))
        }
    }

    fun getCity() {
        val cityPhotoData = getCityNameUseCase.getCityData()
        getPhotoList(cityPhotoData)
    }

    private fun getPhotoList(photoData: PhotoData) {
        listPhotosLiveData.toMutable().postProgress()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoList = getPhotosUseCase.getPhotos(photoData)
                if (photoList.isEmpty()) {
                    listPhotosLiveData.toMutable().postError()
                } else {
                    listPhotosLiveData.toMutable().postSuccess(photoList)
                }
            } catch (e: Exception) {
                listPhotosLiveData.toMutable().postError(e)
            }
        }
    }

    private fun getSearchPhotos(searchData: SearchData) {
        listPhotosLiveData.toMutable().postProgress()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoList = getPhotosUseCase.getSearchPhotos(searchData)
                if (photoList.isEmpty()) {
                    listPhotosLiveData.toMutable().postError()
                } else {
                    listPhotosLiveData.toMutable().postSuccess(photoList)
                }
            } catch (e: Exception) {
                listPhotosLiveData.toMutable().postError(e)
            }
        }
    }

}