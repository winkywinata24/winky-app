package com.example.winky_app

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username: TextView = findViewById(R.id.username)
        val sharedPref = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val usernameVal = sharedPref.getString("username", null)

        username.text = "Hai, $usernameVal"

        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val btnSnackBar1: Button = findViewById(R.id.btnSnackBar1)

        btnSnackBar1.setOnClickListener {
            val view = this.findViewById<View>(android.R.id.content)
            val snackbar = Snackbar.make(view, "Halooha, Ini Snackbar !", Snackbar.LENGTH_LONG)

            snackbar.setAction("Undo") {
                Toast.makeText(this,"Tombol Undo di-klik", Toast.LENGTH_LONG).show()
            }
            snackbar.show()
        }

        val btnSnackBar2: Button = findViewById(R.id.btnSnackBar2)

        btnSnackBar2.setOnClickListener {
            showSnackBar("Ini contoh Snackbar dari Function gess....")
        }

        val btnAlertDialog: Button = findViewById(R.id.btnAlertDialog)

        btnAlertDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("ini Judul Alert")
                .setMessage("Apakah anda yakin")
                .setPositiveButton("Yes"){dialogInterface, which->
                    dialogInterface.dismiss()
                    Toast.makeText(this,"Oke",Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    private fun showSnackBar(message: String) {
        val view = this.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

        snackbar.show()
    }
}