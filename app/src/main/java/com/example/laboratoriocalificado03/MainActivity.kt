package com.example.laboratoriocalificado03

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laboratoriocalificado03.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TeacherAdapter

    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        retrofit = getRetrofit()
        initUI()

    }

    private fun initUI() {
        getAdapter()
        observeValues()
    }

    private fun getAdapter() {
        adapter = TeacherAdapter()
        binding.rvList.setHasFixedSize(true)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    private fun observeValues() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.teacherList.observe(this) { teacherList ->
            adapter.updateTeacherList(teacherList)
        }
    }

 /*
    private fun initUI() {
        getAllTeachers()
        getAdapter()
    }

    private fun getAdapter() {
        adapter = TeacherAdapter()
        binding.rvList.setHasFixedSize(true)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    private fun getAllTeachers() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.create(ApiService::class.java).getTeacher()
            if (response.isSuccessful) {
                response.body()?.let {
                    runOnUiThread {
                        adapter.updateTeacherList(it.teachers)
                        //adapter.updateTeacherList(it.teachers)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("Response", "Error")
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
*/
}