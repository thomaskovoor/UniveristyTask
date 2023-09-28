package com.example.myapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.BuildConfig
import com.example.myapp.dataclass.MainApplication
import com.example.myapp.dataclass.Resource
import com.example.myapp.dataclass.University
import com.example.myapp.network.ApiInterface
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainViewModel : ViewModel() {

    var _universityLiveData = MutableLiveData<Resource<List<University>>>()
    val universityLiveData: MutableLiveData<Resource<List<University>>> get() = _universityLiveData
    val testMsg1 = listOf("Network Error")

    fun getUniversityData(){
     _universityLiveData.postValue(Resource.Loading)

        val builder = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
        // .writeTimeout(60,TimeUnit.SECONDS)
        if (BuildConfig.DEBUG)
        { builder.addInterceptor(OkHttpProfilerInterceptor()) }
        val client = builder.build()
        val BASEURL ="http://universities.hipolabs.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModelScope.launch {

            val apiInterface = retrofit.create(ApiInterface::class.java)
            val call = apiInterface.getUniversityDetails()
            call.enqueue(object :Callback<List<University>?>{
                override fun onResponse(call: Call<List<University>?>, response: Response<List<University>?>) {
                    if(response.isSuccessful){
                        if(response.body()!!.isNotEmpty()){
                            _universityLiveData.postValue(Resource.Success(response.body()!!))
                        }
                    }
                }

                override fun onFailure(call: Call<List<University>?>, t: Throwable) {
                    _universityLiveData.postValue(Resource.Failure(false,0,testMsg1))
                    Toast.makeText(MainApplication.appContext, t.localizedMessage,Toast.LENGTH_LONG).show()
                }

            })
        }


    }
}