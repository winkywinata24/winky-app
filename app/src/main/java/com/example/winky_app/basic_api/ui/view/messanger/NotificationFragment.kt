package com.example.winky_app.basic_api.ui.view.messanger

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.winky_app.R
import com.example.winky_app.basic_api.data.local.database.AppDatabase
import com.example.winky_app.basic_api.data.local.entity.ProductEntity
import com.example.winky_app.basic_api.data.model.NewsHorizontalModel
import com.example.winky_app.basic_api.data.model.ProductPostRequest
import com.example.winky_app.basic_api.data.network.RetrofitInstance
import com.example.winky_app.basic_api.data.repository.ProductRepository
import com.example.winky_app.basic_api.data.repository.UserRepository
import com.example.winky_app.basic_api.ui.adapter.NewsHorizontalAdapter
import com.example.winky_app.basic_api.ui.viewModel.ProductViewModel
import com.example.winky_app.basic_api.ui.viewModel.UserViewModel
import com.example.winky_app.basic_api.utils.Resource
import com.example.winky_app.basic_api.utils.ViewModelFactory
import com.example.winky_app.databinding.FragmentHomeBinding
import com.example.winky_app.databinding.FragmentNotificationBinding
import com.google.android.material.snackbar.Snackbar

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels {
        ViewModelFactory(ProductViewModel::class.java) {
            val repository = ProductRepository(
                RetrofitInstance.getCrudApi(),
                AppDatabase.getDatabase(requireContext()).productDao()
            )
            ProductViewModel(repository)
        }
    }

    private lateinit var adapter: NewsHorizontalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)

        adapter = NewsHorizontalAdapter(mutableListOf())
        binding.productList.adapter = adapter
        binding.productList.layoutManager = LinearLayoutManager(this.context)

        createProductLocal()
        getProductLocal()

//        getProduct()
//        createProduct()

        return binding.root
    }

    private fun getProductLocal() {
        productViewModel.getProductsLocal()
        productViewModel.dataLocal.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("Get Product Room", "Memulai pengambilan data lokal Product")
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.loadingProduct.root.visibility = View.VISIBLE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.GONE
                }

                is Resource.Success -> {
                    Log.d("Get Product Room", "Data lokal Product berhasil diambil")

                    binding.emptyProduct.root.visibility = View.GONE
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.VISIBLE

                    val productItem = resource.data!!.mapIndexed { index, data ->
                        NewsHorizontalModel(
                            "https://images.unsplash.com/photo-1530224264768-7ff8c1789d79?w=1024",
                            data.name
                        )
                    }
                    adapter.updateData(productItem)
                }

                is Resource.Empty -> {
                    Log.e("Get Product Room", resource.message.toString())
                    binding.emptyProduct.root.visibility = View.VISIBLE
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.GONE

                    binding.emptyProduct.emptyMessage.text = resource.message
                }

                is Resource.Error -> {
                    Log.e("Get Product Room", resource.message.toString())
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.VISIBLE
                    binding.productList.visibility = View.GONE

                    binding.errorProduct.errorMessage.text = resource.message

                    binding.errorProduct.retryButton.setOnClickListener {
                        productViewModel.getProductsLocal(true)
                    }
                }
            }
        }
    }

    private fun createProductLocal() {
        val name = "Motor Kawasaki"
        val desc = "Motor ini sangat laju"
        val price = 5200000

        val products = ProductEntity(name = name, description = desc, price = price)

        productViewModel.createProductLocal(products)
        productViewModel.createLocalStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("Insert Product Room", "Memulai penambahan data lokal Product")
                }

                is Resource.Success -> {
                    Log.d("Insert Product Room", "Data lokal Product berhasil ditambahkan!")
                    Snackbar.make(
                        binding.root,
                        "Product created successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    Log.e("Insert Product Room", resource.message.toString())
                    Snackbar.make(
                        binding.root,
                        resource.message ?: "Failed to create product.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }

    private fun getProduct() {
        productViewModel.getProducts(requireContext())
        productViewModel.data.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Empty -> {
                    Log.d("Data Product", "Data Kosong. (${resource.message})")
                    binding.emptyProduct.root.visibility = View.VISIBLE
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.GONE

                    binding.emptyProduct.emptyMessage.text = resource.message
                }

                is Resource.Error -> {
                    Log.e("Data User", resource.message.toString())
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.VISIBLE
                    binding.productList.visibility = View.GONE

                    binding.errorProduct.errorMessage.text = resource.message

                    binding.errorProduct.retryButton.setOnClickListener {
                        productViewModel.getProducts(requireContext(), true)
                    }
                }

                is Resource.Loading -> {
                    Log.d("Data User", "Mohon Tunggu...")
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.loadingProduct.root.visibility = View.VISIBLE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.GONE
                }

                is Resource.Success -> {
                    Log.d("Data User", "Data berhasil didapatkan")
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.VISIBLE

                    val productItem = resource.data!!.items.mapIndexed { index, data ->
                        NewsHorizontalModel(
                            "https://images.unsplash.com/photo-1530224264768-7ff8c1789d79?w=1024",
                            data.name
                        )
                    }
                    adapter.updateData(productItem)
                }
            }
        }
    }

    private fun createProduct() {
        val name = "Buku Ievan"
        val desc = "Ini Deskripsi Buku"
        val price = 250000

        val products = listOf(
            ProductPostRequest(name = name, description = desc, price = price)
        )
        productViewModel.createProduct(requireContext(), products)
        productViewModel.createStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show a loading indicator for create operation
                }

                is Resource.Success -> {

                    Snackbar.make(
                        binding.root,
                        "Product created successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    Snackbar.make(
                        binding.root,
                        resource.message ?: "Failed to create product.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }
}