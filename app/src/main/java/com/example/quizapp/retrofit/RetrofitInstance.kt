package com.example.quizapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.88.80/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: QuestionsAPI by lazy {
        retrofit.create(QuestionsAPI::class.java)
    }
}

