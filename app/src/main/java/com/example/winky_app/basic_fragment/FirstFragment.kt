package com.example.winky_app.basic_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.winky_app.R

class FirstFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val btnGoToFragment2: Button = view.findViewById(R.id.button6)

        btnGoToFragment2.setOnClickListener {
            (activity as? FragmentActivity)?.replaceFragment(SecondFragment())
        }

        return view
    }
}