package com.example.quizapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp.model.Question
import com.example.quizapp.retrofit.QuestionsAPI
import com.example.quizapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository {

    private val questionsAPI: QuestionsAPI = RetrofitInstance.api

    suspend fun getQuestionsFromAPI(): LiveData<List<Question>> {
        val data = MutableLiveData<List<Question>>()
        withContext(Dispatchers.IO) {
            try {
                val response = questionsAPI.getQuestions()
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.postValue(it)
                    }
                } else {
                    Log.e("QuizRepository", "Failed to fetch questions: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("QuizRepository", "Exception while fetching questions: ${e.message}")
            }
        }
        return data
    }
}
