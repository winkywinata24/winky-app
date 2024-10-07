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

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref: SharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val login: Button = findViewById(R.id.Login)
        val regist: Button = findViewById(R.id.btnRegist)

        val usernameVal = sharedPref.getString("username", null)
        val passwordVal = sharedPref.getString("password", null)

        login.setOnClickListener {
            val a = username.text.toString()
            val b = password.text.toString()

            if (a == usernameVal && b == passwordVal) {
                val editor = sharedPref.edit()
                editor.putString("username", a)
                editor.apply()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                Toast.makeText(this,"Login", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Salah Input", Toast.LENGTH_LONG).show()
            }
        }

        regist.setOnClickListener {
            val i = Intent(this, RegistActivity::class.java)
            startActivity(i)
        }
    }
}