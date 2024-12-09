package com.example.winky_app.basic_api.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.winky_app.basic_api.ui.view.messanger.InformationFragment
import com.example.winky_app.basic_api.ui.view.messanger.NotificationFragment

class MessageTabAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragmentList = listOf(
        NotificationFragment(),
        InformationFragment(),
        InformationFragment(),
        InformationFragment(),
        InformationFragment(),
        InformationFragment(),
        InformationFragment(),
        InformationFragment(),
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}