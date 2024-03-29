package com.yuriykyus.walry.data.network.api

import com.yuriykyus.walry.data.network.response.PhotosData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoListApi {
    @GET("/services/rest/")
    suspend fun getPhotoList(
        @Query("method") method: String,
        @Query("api_key") key: String,
        @Query("tags") tags: String,
        @Query("text") text: String,
        @Query("content_type") type: Int,
        @Query("format") format: String,
        @Query("nojsoncallback") callBack: String
    ): PhotosData?

    @GET("/services/rest/")
    suspend fun getSearchPhotoList(
        @Query("method") method: String,
        @Query("api_key") key: String,
        @Query("tags") tags: String,
        @Query("text") text: String,
        @Query("content_type") type: Int,
        @Query("format") format: String,
        @Query("nojsoncallback") callBack: String
    ): PhotosData?
}