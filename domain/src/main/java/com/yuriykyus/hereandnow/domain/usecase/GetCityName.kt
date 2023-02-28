package com.yuriykyus.hereandnow.domain.usecase

import com.yuriykyus.hereandnow.domain.models.CityName
import com.yuriykyus.hereandnow.domain.repository.CityRepository

class GetCityName(private val cityRepository: CityRepository) {
    fun execute(): CityName {
        return cityRepository.getCityName()
    }
}