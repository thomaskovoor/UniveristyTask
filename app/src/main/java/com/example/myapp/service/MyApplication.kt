package com.example.myapp.service

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.viewmodel.MainViewModel

class MyApplication : Application() {
    val myViewModel: MainViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)

}