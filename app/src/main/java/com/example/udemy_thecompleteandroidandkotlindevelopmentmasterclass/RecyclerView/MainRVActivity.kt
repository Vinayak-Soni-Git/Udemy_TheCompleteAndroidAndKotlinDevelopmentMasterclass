package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.RecyclerView.models.RVItem

class MainRVActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_rvactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val list = generateList(20)
        adapter = RVAdapter(this@MainRVActivity, list)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
    }

    private fun generateList(size: Int): MutableList<RVItem> {
        val list = mutableListOf<RVItem>()

        for (i in 0 until size)
            list.add(RVItem("Title $i", "Description $i"))

        return list
    }
}