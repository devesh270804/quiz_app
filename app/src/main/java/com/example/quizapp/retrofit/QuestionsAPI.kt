package com.example.quizapp.retrofit

import com.example.quizapp.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET


interface QuestionsAPI {
    @GET("quiz/questionapi.php")
    suspend fun getQuestions() : Response<QuestionsList>
}