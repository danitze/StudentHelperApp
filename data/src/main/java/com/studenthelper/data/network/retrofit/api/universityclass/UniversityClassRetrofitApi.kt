package com.studenthelper.data.network.retrofit.api.universityclass

import com.studenthelper.data.network.retrofit.api.universityclass.model.UniversityClassRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UniversityClassRetrofitApi {

    @GET("class")
    suspend fun getAllUniversityClasses(
        @Query("from_date") fromDate: String,
        @Query("to_date") toDate: String
    ): List<UniversityClassRemoteModel>

    @GET("class/{id}")
    suspend fun getUniversityClass(
        @Path("id") id: Long
    ): UniversityClassRemoteModel

}