package com.example.winky_app.quiz_1

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.winky_app.R
import com.example.winky_app.basic_listview.ListAdapter

class Quiz1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.listView)

        val menuList = listOf(
            QuizModel("Menghitung Luas Jajar Genjang",  R.drawable.ic_menu),
            QuizModel("Cara Membuat Smoothie Buah", R.drawable.ic_home),
        )

        val adapter = QuizAdapter(this, menuList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = menuList[position]
            if (selectedItem.name == "Menghitung Luas Jajar Genjang") {
                val i = Intent(this, HitungActivity::class.java)
                startActivity(i)
            } else if (selectedItem.name == "Cara Membuat Smoothie Buah") {
                val i = Intent(this, TutorialActivity::class.java)
                startActivity(i)
            }
        }
    }
}