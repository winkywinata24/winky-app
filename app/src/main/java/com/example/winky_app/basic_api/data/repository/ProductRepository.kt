package com.example.winky_app.basic_api.data.repository

import com.example.winky_app.basic_api.data.local.dao.ProductDao
import com.example.winky_app.basic_api.data.local.entity.ProductEntity
import com.example.winky_app.basic_api.data.model.ProductPostRequest
import com.example.winky_app.basic_api.data.model.ProductResponse
import com.example.winky_app.basic_api.data.network.ApiService
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val api: ApiService,
    private val productDao: ProductDao
) {
    private val tokenBearer = "Bearer BKYVnXB_Na6lPaUiPbXLakTHjkumP5UNj89UTtLpZnLI_b3dIw"

    suspend fun fetchProduct(): ProductResponse {
        return api.getProducts(tokenBearer)
    }

    suspend fun createProduct(products: List<ProductPostRequest>): ProductResponse {
        return api.createProduct(tokenBearer, products)
    }

    suspend fun insertProductRoom(product: ProductEntity){
        return productDao.insertProduct(product)
    }

//    suspend fun deleteProductRoomById(id: Int) {
//        return productDao.deleteProductById(id)
//    }

    suspend fun deleteProductRoom(product: ProductEntity) {
        return productDao.deleteProduct(product)
    }

    fun getProductRoom(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }
}