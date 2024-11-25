package com.example.winky_app.menu.messanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winky_app.R
import com.example.winky_app.databinding.FragmentHomeBinding
import com.example.winky_app.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private var _binding : FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }
}