package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView.utils.Constants

class SecondActivity : AppCompatActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTitle = findViewById(R.id.tv_title)
        tvDescription = findViewById(R.id.tv_description)

        val data = intent.extras
        data?.let {
            tvTitle.text = it.getString(Constants.KEY_TITLE)
            tvDescription.text = it.getString(Constants.KEY_DESCRIPTION)
        }
    }
}