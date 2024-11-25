package com.example.winky_app.menu.messanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.winky_app.R
import com.example.winky_app.databinding.FragmentMessageBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MessageFragment : Fragment() {
    private var _binding : FragmentMessageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_message, container, false)
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
//
//        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
//        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)

        binding.viewPager.adapter = MessageTabAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Notification"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                1 -> {
                    tab.text = "Information"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                2 -> {
                    tab.text = "Information1"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                3 -> {
                    tab.text = "Information2"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                4 -> {
                    tab.text = "Information3"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                5 -> {
                    tab.text = "Information4"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                6 -> {
                    tab.text = "Information5"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }

                7 -> {
                    tab.text = "Information6"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_message)
                }
            }
        }.attach()
        return binding.root
    }
}