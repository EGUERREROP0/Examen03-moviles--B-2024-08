package com.example.laboratoriocalificado03

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("list/teacher-b")
    suspend fun getTeacher(): Response<ResponseData>

}