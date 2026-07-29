package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.QuizApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.QuizApp.utils.Constants
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R

class ResultActivity : AppCompatActivity() {
    private lateinit var score: TextView
    private lateinit var name: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        score = findViewById(R.id.tv_score)
        name = findViewById(R.id.tv_name)
        finishButton = findViewById(R.id.btn_finish)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val scoreExtra = intent.getIntExtra(Constants.SCORE, 0)
        val nameExtra = intent.getStringExtra(Constants.USER_NAME)

        score.text = "Your score is $scoreExtra out of $totalQuestions"
        name.text = nameExtra

        finishButton.setOnClickListener {
            Intent(this@ResultActivity, MainQuizActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}