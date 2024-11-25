package com.example.winky_app.menu.messanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winky_app.R
import com.example.winky_app.databinding.FragmentHomeBinding
import com.example.winky_app.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private var _binding : FragmentInformationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_information, container, false)
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }
}