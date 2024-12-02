package com.example.winky_app.basic_api.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.winky_app.R
import com.example.winky_app.basic_api.data.model.NewsHorizontalModel
import com.example.winky_app.basic_api.data.network.RetrofitInstance
import com.example.winky_app.basic_api.data.repository.UserRepository
import com.example.winky_app.basic_api.ui.adapter.AutoSliderAdapter
import com.example.winky_app.basic_api.ui.adapter.NewsHorizontalAdapter
import com.example.winky_app.basic_api.ui.viewModel.UserViewModel
import com.example.winky_app.basic_api.utils.Resource
import com.example.winky_app.basic_api.utils.ViewModelFactory
import com.example.winky_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels {
        ViewModelFactory(UserViewModel::class.java) {
            val repository = UserRepository(RetrofitInstance.getJsonPlaceHolderApi())
            UserViewModel(repository)
        }
    }

//    private val userViewModel: UserViewModel by lazy {
//        val repository = UserRepository(RetrofitInstance.getJsonPlaceHolderApi())
//        ViewModelProvider(
//            this,
//            ViewModelFactory(UserViewModel::class.java) { UserViewModel(repository) }
//        )[UserViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupAutoSlider(binding)
        setupGridMenu(binding)
//        setupNewsHorizontal(binding)
        setupNewsHorizontalApi(binding)
        return binding.root
    }

    private fun setupAutoSlider(binding: FragmentHomeBinding) {
        val images = listOf(
            R.drawable.ic_home,
            R.drawable.ic_menu,
            R.drawable.logo
        )
//        val viewPager : ViewPager2 = view.findViewById(R.id.autoSlider)
//        val dotsIndicator : WormDotsIndicator = view.findViewById(R.id.worn_indicator)

        binding.autoSlider.adapter = AutoSliderAdapter(images, binding.autoSlider)
        binding.wornIndicator.attachTo(binding.autoSlider)
    }

    private fun setupGridMenu(binding: FragmentHomeBinding) {

        binding.includeHomeMenuGrid.menu1.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 1", Toast.LENGTH_LONG).show()
        }
        binding.includeHomeMenuGrid.menu2.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 2", Toast.LENGTH_LONG).show()
        }
        binding.includeHomeMenuGrid.menu3.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 3", Toast.LENGTH_LONG).show()
        }
        binding.includeHomeMenuGrid.menu4.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 4", Toast.LENGTH_LONG).show()
        }
        binding.includeHomeMenuGrid.menu5.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 5", Toast.LENGTH_LONG).show()
        }
        binding.includeHomeMenuGrid.menu6.setOnClickListener {
            Toast.makeText(context, "Anda Klik Menu 6", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupNewsHorizontalApi(binding: FragmentHomeBinding) {
        val adapter = NewsHorizontalAdapter(emptyList())
//        userViewModel.getUsers().observe(requireActivity()) { resp ->
//            if(resp != null) {
//                val newsItem = resp.mapIndexed{index, data ->
//                    NewsHorizontalModel("https://images.unsplash.com/photo-1516117172878-fd2c41f4a759?w=1024", data.name)
//                }
//                adapter.updateData(newsItem)
//            }
//        }
        userViewModel.getUsers(requireContext())
        userViewModel.data.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Empty -> {
                    binding.emptyNewHoriList.root.visibility = View.VISIBLE
                    binding.loadingNewHoriList.root.visibility = View.GONE
                    binding.errorNewHoriList.root.visibility = View.GONE
                    binding.newHoriList.visibility = View.GONE
                    binding.emptyNewHoriList.emptyMessage.text = resource.message
                    Log.d("Data User", "Data Kosong. (${resource.message})")
                }

                is Resource.Error -> {
                    binding.emptyNewHoriList.root.visibility = View.GONE
                    binding.loadingNewHoriList.root.visibility = View.GONE
                    binding.errorNewHoriList.root.visibility = View.VISIBLE
                    binding.newHoriList.visibility = View.GONE
                    binding.errorNewHoriList.errorMessage.text = resource.message
                    binding.errorNewHoriList.retryButton.setOnClickListener {
                        userViewModel.getUsers(requireContext(), true)
                    }
                    Log.d("Data User", resource.message.toString())
                }

                is Resource.Loading -> {
                    binding.emptyNewHoriList.root.visibility = View.GONE
                    binding.loadingNewHoriList.root.visibility = View.VISIBLE
                    binding.errorNewHoriList.root.visibility = View.GONE
                    binding.newHoriList.visibility = View.GONE
                    Log.d("Data User", "Mohon Tunggu...")
                }

                is Resource.Success -> {
                    binding.emptyNewHoriList.root.visibility = View.GONE
                    binding.loadingNewHoriList.root.visibility = View.GONE
                    binding.errorNewHoriList.root.visibility = View.GONE
                    binding.newHoriList.visibility = View.VISIBLE
                    Log.d("Data User", "Data berhasil didapatkan")
                    val newsItem = resource.data!!.mapIndexed { index, data ->
                        NewsHorizontalModel(
                            "https://images.unsplash.com/photo-1516117172878-fd2c41f4a759?w=1024",
                            data.name
                        )
                    }
                    adapter.updateData(newsItem)
                }
            }
        }
        binding.newHoriList.adapter = adapter
        binding.newHoriList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }
}