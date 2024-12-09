package com.example.winky_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.winky_app.basic_api.data.firebase.FirebaseAuthService
import com.example.winky_app.basic_api.data.repository.FirebaseRepository
import com.example.winky_app.forgot_password.ForgotActivity
import com.example.winky_app.basic_api.ui.view.MainActivity
import com.example.winky_app.basic_api.ui.viewModel.FirebaseViewModel
import com.example.winky_app.basic_api.utils.Resource
import com.example.winky_app.basic_api.utils.ViewModelFactory
import com.example.winky_app.quiz_1.Quiz1Activity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val firebaseViewModel: FirebaseViewModel by viewModels {
        ViewModelFactory(FirebaseViewModel::class.java) {
            val repository = FirebaseRepository(FirebaseAuthService())
            FirebaseViewModel(repository)
        }
    }

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
//        val isLogin = sharedPref.getString("isLogin", null)
        val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
        if (isLoggedIn) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val login: Button = findViewById(R.id.Login)
        val regist: Button = findViewById(R.id.btnRegist)

        val usernameVal = sharedPref.getString("username", null)
        val passwordVal = sharedPref.getString("password", null)

        firebaseViewModel.loginState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("Firebase User Authentication","Mengirim Username Password...")
                }
                is Resource.Success -> {
                    Log.d("Firebase User Authetication","Halo ${firebaseViewModel.getCurrentUser().toString()}")
                    val user = resource.data
                    val editor = sharedPref.edit()

                    //Tidak perlu lagi, karena sudah dihandle oleh FirebaseAuth.getInstance().currentUser
                    //editor.putString("isLogin", "1")

                    editor.putString("username", user?.username)
                    editor.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is Resource.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }

        login.setOnClickListener {
            val username = username.text.toString().trim()
            val password = password.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            firebaseViewModel.login(username, password)
        }

        regist.setOnClickListener {
            val i = Intent(this, RegistActivity::class.java)
            startActivity(i)
        }

        val btnForgot: TextView = findViewById(R.id.textView3)

        btnForgot.setOnClickListener {
            val i = Intent(this, ForgotActivity::class.java)
            startActivity(i)
        }

        val btnQuiz: Button = findViewById(R.id.button13)

        btnQuiz.setOnClickListener {
            val i = Intent(this, Quiz1Activity::class.java)
            startActivity(i)
        }
    }
}