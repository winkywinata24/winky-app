package com.example.winky_app.basic_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.winky_app.R

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val btnGoToFragment3: Button = view.findViewById(R.id.button7)

        btnGoToFragment3.setOnClickListener {
            val thirdFragment = ThirdFragment()

            val bundle = Bundle()
            bundle.putString("link","https://tokopedia.com")
            thirdFragment.arguments = bundle
            (activity as? FragmentActivity)?.replaceFragment(thirdFragment)
        }

        return view
    }
}