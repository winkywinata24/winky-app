package com.example.winky_app

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username: TextView = findViewById(R.id.textView)
        val sharedPref = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val usernameVal = sharedPref.getString("username", null)

        username.text = "Hai, $usernameVal"

        val input1: EditText = findViewById(R.id.input1)
        val btnHitung1: Button = findViewById(R.id.btnHitung1)
        val result1: TextView = findViewById(R.id.result1)
        var c = 0

        btnHitung1.setOnClickListener {
            if (input1.text.toString() == "") {
                showSnackBar("Input Angka!")
            } else {
                var a = input1.text.toString()
                var b = a.toInt() * a.toInt()
                result1.setText(b.toString())
                c = a.toInt()
            }
        }

        val input2: EditText = findViewById(R.id.input2)
        val btnHitung2: Button = findViewById(R.id.btnHitung2)
        val result2: TextView = findViewById(R.id.result2)

        btnHitung2.setOnClickListener {
            if (input1.text.toString() == "" || input2.text.toString() == "") {
                showSnackBar("Input Angka!")
            } else {
                var a = input2.text.toString()
                var b = a.toInt() * c.toInt()
                result2.setText(b.toString())
            }
        }

        val btnNext: Button = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            i.putExtra("username","Winky")
            i.putExtra("password","xxxxx")
            startActivity(i)
        }

        val btnLogout: Button = findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Mau Logout?")
                .setPositiveButton("Yes"){dialogInterface, which->
                    dialogInterface.dismiss()
                    Toast.makeText(this,"Logout", Toast.LENGTH_LONG).show()
                    finish()
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