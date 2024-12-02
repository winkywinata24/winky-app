package com.example.winky_app.basic_api.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.winky_app.R
import com.example.winky_app.basic_api.ui.view.home.HomeFragment
import com.example.winky_app.basic_api.ui.view.messanger.MessageFragment
import com.example.winky_app.basic_api.ui.view.more.MoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        loadFragment(HomeFragment())

        val bottonNav : BottomNavigationView = findViewById(R.id.bottom_nav)

        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                }
                R.id.message -> {
                    loadFragment(MessageFragment())
                }
                R.id.more -> {
                    loadFragment(MoreFragment())
                }
            }
            true
        }
    }

    private fun loadFragment (fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_bottomnav, fragment)
        transaction.commit()
    }
}