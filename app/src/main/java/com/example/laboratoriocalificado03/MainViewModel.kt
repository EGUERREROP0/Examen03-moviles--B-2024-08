package com.example.laboratoriocalificado03

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel()  {

    val teacherList = MutableLiveData<List<Teacher>>()

    val isLoading = MutableLiveData<Boolean>()

    init{
        getAllTeachers()
    }

    private fun getAllTeachers(){
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(ApiService::class.java).getTeacher()
                if(call.isSuccessful) {
                    call.body()?.let {
                        isLoading.postValue(false)
                        teacherList.postValue(it.teachers)
                    }
                }
            } catch (e: Exception) {
               // errorApi.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}