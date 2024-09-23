package com.example.winky_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var input1: EditText = findViewById(R.id.input1)
        var btnHitung1: Button = findViewById(R.id.btnHitung1)
        var result1: TextView = findViewById(R.id.result1)
        var c = 0

        btnHitung1.setOnClickListener {
            if (input1.text.toString() == "") {
                result1.setText("NULL")
            } else {
                var a = input1.text.toString()
                var b = a.toInt() * a.toInt()
                result1.setText(b.toString())
                c = a.toInt()
            }
        }

        var input2: EditText = findViewById(R.id.input2)
        var btnHitung2: Button = findViewById(R.id.btnHitung2)
        var result2: TextView = findViewById(R.id.result2)

        btnHitung2.setOnClickListener {
            if (input1.text.toString() == "" || input2.text.toString() == "") {
                result2.setText("NULL")
            } else {
                var a = input2.text.toString()
                var b = a.toInt() * c.toInt()
                result2.setText(b.toString())
            }
        }
    }
}