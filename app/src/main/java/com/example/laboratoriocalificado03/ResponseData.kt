package com.example.laboratoriocalificado03

data class ResponseData(
    val teachers: List<Teacher>
)

data class Teacher(
    val email: String,
    val image_url: String,
    val last_name: String,
    val name: String,
    val phone_number: String
)