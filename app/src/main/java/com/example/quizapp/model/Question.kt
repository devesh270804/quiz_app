package com.example.quizapp.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("correct")
    val correct: String,

    @SerializedName("option1")
    val option1: String,

    @SerializedName("option2")
    val option2: String,

    @SerializedName("option3")
    val option3: String,

    @SerializedName("option4")
    val option4: String,

    @SerializedName("question")
    val question: String
)