package com.example.myapp.network

import com.example.myapp.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object{

        fun getRerofitInstance() : Retrofit? {

            val builder = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
            // .writeTimeout(60,TimeUnit.SECONDS)
            if (BuildConfig.DEBUG)
            { builder.addInterceptor(OkHttpProfilerInterceptor()) }
            val client = builder.build()
            val BASEURL ="http://universities.hipolabs.com"

            val retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit

        }

    }
}