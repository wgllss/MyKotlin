package com.example.myapplication.data.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(var name: String? = null, var age: Int, var country: String = "中国")