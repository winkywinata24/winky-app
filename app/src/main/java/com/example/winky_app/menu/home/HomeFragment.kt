package com.example.winky_app.menu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.winky_app.R
import com.example.winky_app.basic_recyclerview.ItemModel
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setupGridMenu(view)
        setupNewsHorizontal(view)
        setupAutoSlider(view)
        return view
    }

    private fun setupAutoSlider(view: View) {
        val images = listOf(
            R.drawable.ic_home,
            R.drawable.ic_menu,
            R.drawable.logo
        )
        val viewPager : ViewPager2 = view.findViewById(R.id.autoSlider)
        val dotsIndicator : WormDotsIndicator = view.findViewById(R.id.worn_indicator)

        viewPager.adapter = AutoSliderAdapter(images, viewPager)
        dotsIndicator.attachTo(viewPager)
    }

    private fun setupGridMenu(view: View) {
        val cardMenu1 : CardView = view.findViewById(R.id.menu1)
        val cardMenu2 : CardView = view.findViewById(R.id.menu2)
        val cardMenu3 : CardView = view.findViewById(R.id.menu3)
        val cardMenu4 : CardView = view.findViewById(R.id.menu4)
        val cardMenu5 : CardView = view.findViewById(R.id.menu5)
        val cardMenu6 : CardView = view.findViewById(R.id.menu6)

        cardMenu1.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 1", Toast.LENGTH_LONG).show()
        }
        cardMenu2.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 2", Toast.LENGTH_LONG).show()
        }
        cardMenu3.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 3", Toast.LENGTH_LONG).show()
        }
        cardMenu4.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 4", Toast.LENGTH_LONG).show()
        }
        cardMenu5.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 5", Toast.LENGTH_LONG).show()
        }
        cardMenu6.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 6", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupNewsHorizontal(view: View) {
        val newsItem = listOf(
            NewsHorizontalModel("https://images.unsplash.com/photo-1516117172878-fd2c41f4a759?w=1024", "News 1"),
            NewsHorizontalModel("https://images.unsplash.com/photo-1532009324734-20a7a5813719?w=1024", "News 2"),
            NewsHorizontalModel("https://images.unsplash.com/photo-1524429656589-6633a470097c?w=1024", "News 3"),
            NewsHorizontalModel("https://images.unsplash.com/photo-1530224264768-7ff8c1789d79?w=1024", "News 4"),
            NewsHorizontalModel("https://images.unsplash.com/photo-1501594907352-04cda38ebc29?w=1024", "News 5")
        )
        val newsHorizontal : RecyclerView = view.findViewById(R.id.newHoriList)
        newsHorizontal.adapter = NewsHorizontalAdapter(newsItem)
        newsHorizontal.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }
}