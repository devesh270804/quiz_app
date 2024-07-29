package com.example.quizapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.model.Question
import com.example.quizapp.viewmodel.QuizViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quizViewModel: QuizViewModel
    private var questionsList: ArrayList<Question> = ArrayList()

    companion object {
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Resetting the scores
        result = 0
        totalQuestions = 0

        // Getting the ViewModel
        quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        // Observing the LiveData from ViewModel
        quizViewModel.questionsLiveData.observe(this) { questions ->
            if (!questions.isNullOrEmpty()) {
                questionsList = questions as ArrayList<Question>
                val firstQuestion = questions[0]
                Log.i("TAGY", "This is the first question: ${firstQuestion.question}")
                binding.apply {
                    txtQuestion.text = firstQuestion.question
                    radio1.text = firstQuestion.option1
                    radio2.text = firstQuestion.option2
                    radio3.text = firstQuestion.option3
                    radio4.text = firstQuestion.option4
                }
            }
        }

        // Adding click listeners to the buttons
        var i = 1
        binding.apply {
            btnNext.setOnClickListener {
                val selectedOption = radioGroup.checkedRadioButtonId
                if (selectedOption != -1) {
                    val radbutton = findViewById<RadioButton>(selectedOption)

                    questionsList.let {
                        if (i < it.size) {
                            totalQuestions = it.size
                            if (radbutton.text.toString() == it[i - 1].correct) {
                                result++
                                txtResult.text = "Correct Answer: $result"
                            }
                            txtQuestion.text = "Question ${i + 1}: ${questionsList[i].question}"
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            // Checking if it is the last question
                            if (i == it.size - 1) {
                                btnNext.text = "FINISH"
                            }
                            radioGroup.clearCheck()
                            i++
                        } else {
                            if (radbutton.text.toString() == it[i - 1].correct) {
                                result++
                                txtResult.text = "Correct Answer: $result"
                            }
                            val intent = Intent(this@MainActivity, ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Please select one option", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
