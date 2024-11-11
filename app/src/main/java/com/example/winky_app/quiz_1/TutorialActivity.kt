package com.example.winky_app.quiz_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.winky_app.LoginActivity
import com.example.winky_app.R
import com.example.winky_app.welcome_screen.PagerAdapter
import com.example.winky_app.welcome_screen.Welcome1Fragment
import com.example.winky_app.welcome_screen.Welcome2Fragment
import com.example.winky_app.welcome_screen.Welcome3Fragment
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class TutorialActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager2
    private lateinit var dotsIndicator : DotsIndicator
    private lateinit var btnNext : Button
    private lateinit var btnSkip : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tutorial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.viewPager)
        dotsIndicator = findViewById(R.id.dot_indicator)
        btnNext = findViewById(R.id.button11)
        btnSkip = findViewById(R.id.button10)

        val fragmentList = listOf(Tutorial1Fragment(), Tutorial2Fragment(), Tutorial3Fragment(), Tutorial4Fragment())
        val adapter = com.example.winky_app.quiz_1.PagerAdapter(this, fragmentList)
        viewPager.adapter = adapter

        dotsIndicator.attachTo(viewPager)

        btnNext.setOnClickListener {
            if(viewPager.currentItem < fragmentList.size -1) {
                viewPager.currentItem += 1
            } else {
                finishTutorialActivity()
            }
        }

        btnSkip.setOnClickListener {
            finishTutorialActivity()
        }

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == fragmentList.size - 1) {
                    btnNext.text = "Finish"
                    btnSkip.visibility = View.GONE
                } else {
                    btnNext.text = "Next"
                    btnSkip.visibility = View.VISIBLE
                }
            }
        })
    }
    private fun finishTutorialActivity() {
        startActivity(Intent(this, Quiz1Activity::class.java))
        finish()
    }
}