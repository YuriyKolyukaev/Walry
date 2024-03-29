package com.yuriykyus.walry.data.repository

import com.yuriykyus.walry.data.storage.models.City
import com.yuriykyus.walry.data.storage.CityStorage
import com.yuriykyus.walry.domain.models.PhotoData
import com.yuriykyus.walry.domain.repository.CityRepository

class CityRepositoryImpl(private val cityStorageImpl: CityStorage) : CityRepository {

    override fun getCityName(): PhotoData {
        val city = cityStorageImpl.getName()
        return mapToDomain(city = city)
    }

    override fun saveCityName(photoData: PhotoData): Boolean {
        val city = mapToStorage(photoData)
        return cityStorageImpl.saveName(city = city)
    }

    private fun mapToStorage(photoData: PhotoData): City {
        return City(cityName = photoData.text)
    }

    private fun mapToDomain(city: City): PhotoData {
        return PhotoData("Архитектура", city.cityName)
    }
}