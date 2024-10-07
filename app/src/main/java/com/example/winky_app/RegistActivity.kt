package com.example.winky_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_regist)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref: SharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val confirmPass: EditText = findViewById(R.id.confirmPass)
        val login: Button = findViewById(R.id.Login)
        val regist: Button = findViewById(R.id.regist)

        regist.setOnClickListener {
            val a = username.text.toString()
            val b = password.text.toString()
            val c = confirmPass.text.toString()
            if (a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty() && b == c) {
                val editor = sharedPref.edit()
                editor.putString("username", a)
                editor.putString("password", b)
                editor.apply()
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                Toast.makeText(this,"Berhasil Regist", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Tidak Berhasil Regist", Toast.LENGTH_LONG).show()
            }
        }
        login.setOnClickListener {
            finish()
        }
    }
}