package com.studenthelper.data.network.retrofit.api.universityclass

import com.studenthelper.data.network.retrofit.api.universityclass.model.AddHomeTaskRemoteModel
import com.studenthelper.data.network.retrofit.api.universityclass.model.AddMeetingLinkRemoteModel
import com.studenthelper.data.network.retrofit.api.universityclass.model.UniversityClassRemoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
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

    @PUT("class/{id}/hometask")
    suspend fun addHomeTask(
        @Path("id") id: Long,
        @Body model: AddHomeTaskRemoteModel
    )

    @DELETE("class/{id}/hometask")
    suspend fun deleteHomeTask(
        @Path("id") id: Long
    )

    @PUT("class/{id}/link")
    suspend fun addLink(
        @Path("id") id: Long,
        @Body model: AddMeetingLinkRemoteModel
    )

    @PUT("class/series/{seriesId}/link")
    suspend fun addLinkToSeries(
        @Path("seriesId") seriesId: String,
        @Body model: AddMeetingLinkRemoteModel
    )

    @DELETE("class/{id}/link")
    suspend fun deleteLink(
        @Path("id") id: Long,
    )

    @DELETE("class/series/{seriesId}/link")
    suspend fun deleteLinkFromSeries(
        @Path("seriesId") seriesId: String,
    )

}