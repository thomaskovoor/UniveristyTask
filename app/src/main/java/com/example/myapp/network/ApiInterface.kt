package com.example.myapp.network

import com.example.myapp.dataclass.University
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("search")
    fun getUniversityDetails() : Call<List<University>>
}