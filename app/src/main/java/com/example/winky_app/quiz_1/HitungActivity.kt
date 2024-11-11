package com.example.winky_app.quiz_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.winky_app.R

class HitungActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hitung)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tinggi: EditText = findViewById(R.id.editTextText2)
        val alas: EditText = findViewById(R.id.editTextText3)
        val hitung: Button = findViewById(R.id.button14)
        val hasil: TextView = findViewById(R.id.textView7)

        hitung.setOnClickListener {
            var a = tinggi.text.toString()
            var b = alas.text.toString()
            var c = a.toInt() * b.toInt()
            hasil.setText(c.toString())
        }
    }
}