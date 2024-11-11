package com.example.winky_app.forgot_password

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.winky_app.LoginActivity
import com.example.winky_app.R

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailText: TextView = findViewById(R.id.textDesc)
        val email: EditText = findViewById(R.id.editTextText)
        val btnSubmit: Button = findViewById(R.id.button)

        btnSubmit.setOnClickListener {
            if (email.text.toString() == "") {
                AlertDialog.Builder(this)
                    .setTitle("Gagal")
                    .setMessage("Input Email")
                    .setPositiveButton("OK") { dialogInterface, which ->
                        dialogInterface.dismiss()
                    }
                    .setNegativeButton("", null)
                    .show()
            } else {
                if (email.text.toString() == "2255301198@mahasiswa.pcr.ac.id") {
                    AlertDialog.Builder(this)
                        .setTitle("Berhasil")
                        .setMessage("Link reset password sudah dikirim ke 2255301198@mahasiswa.pcr.ac.id. Silahkan akses dan ikuti langkah yang tersedia.")
                        .setPositiveButton("OK") { dialogInterface, which ->
                            dialogInterface.dismiss()
                            email.visibility = View.GONE
                            btnSubmit.visibility = View.GONE
                            emailText.text = "Berhasil"
                        }
                        .setNegativeButton("", null)
                        .show()
                } else {
                    AlertDialog.Builder(this)
                        .setTitle("Gagal")
                        .setMessage("Email yang dimasukkan tidak valid")
                        .setPositiveButton("OK") { dialogInterface, which ->
                            dialogInterface.dismiss()
                        }
                        .setNegativeButton("", null)
                        .show()
                }
            }
        }

    }
}