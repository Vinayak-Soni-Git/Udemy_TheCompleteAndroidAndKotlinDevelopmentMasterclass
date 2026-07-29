package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.QuizApp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.QuizApp.models.Question
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.QuizApp.utils.Constants
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var ivFlag: ImageView
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView

    private var currentPosition = 1
    private lateinit var questionsList: MutableList<Question>
    private lateinit var checkButton: Button
    private var selectedOptionPosition = 0
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private var score = 0

    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivFlag = findViewById(R.id.iv_flag)

        checkButton = findViewById(R.id.btn_check)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener {

        }

        questionsList = Constants.getQuestions()

        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }

    }

    private fun showNextQuestion() {
        val question = questionsList[currentPosition - 1]
        ivFlag.setImageResource(question.image)
        progressBar.progress = currentPosition
        tvProgress.text = "$currentPosition/${progressBar.max}"

        tvQuestion.text = question.question
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour

        if (currentPosition < questionsList.size) {
            checkButton.text = "Check Answer"
            currentQuestion = questionsList[currentPosition]
        } else {
            checkButton.text = "Finish"
            Intent(this@QuestionsActivity, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                startActivity(it)
            }
        }
        currentPosition++
        answered = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(tvOptionOne)
        options.add(tvOptionTwo)
        options.add(tvOptionThree)
        options.add(tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.quiz_default_option_bg)
        }
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()
        selectedOptionPosition = selectedOptionNumber
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background =
            ContextCompat.getDrawable(this, R.drawable.quiz_selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                selectedOption(tvOptionOne, 1)
            }

            R.id.tv_option_two -> {
                selectedOption(tvOptionTwo, 2)
            }

            R.id.tv_option_three -> {
                selectedOption(tvOptionThree, 3)
            }

            R.id.tv_option_four -> {
                selectedOption(tvOptionFour, 4)
            }

            R.id.btn_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true
        if (selectedAnswer == currentQuestion.correctAnswer) {
            highLightAnswer(selectedAnswer)
            score++
        } else {
            when (selectedAnswer) {
                1 -> {
                    tvOptionOne.background =
                        ContextCompat.getDrawable(this, R.drawable.quiz_wrong_option_border_bg)
                }

                2 -> {
                    tvOptionTwo.background =
                        ContextCompat.getDrawable(this, R.drawable.quiz_wrong_option_border_bg)
                }

                3 -> {
                    tvOptionThree.background =
                        ContextCompat.getDrawable(this, R.drawable.quiz_wrong_option_border_bg)
                }

                4 -> {
                    tvOptionFour.background =
                        ContextCompat.getDrawable(this, R.drawable.quiz_wrong_option_border_bg)
                }
            }
        }
        checkButton.text = "Next Question"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highLightAnswer(selectedAnswer)
    }

    private fun highLightAnswer(answer: Int) {
        when (answer) {
            1 -> {
                tvOptionOne.background =
                    ContextCompat.getDrawable(this, R.drawable.quiz_correct_option_border_bg)
            }

            2 -> {
                tvOptionTwo.background =
                    ContextCompat.getDrawable(this, R.drawable.quiz_correct_option_border_bg)
            }

            3 -> {
                tvOptionThree.background =
                    ContextCompat.getDrawable(this, R.drawable.quiz_correct_option_border_bg)
            }

            4 -> {
                tvOptionFour.background =
                    ContextCompat.getDrawable(this, R.drawable.quiz_correct_option_border_bg)
            }
        }
    }
}