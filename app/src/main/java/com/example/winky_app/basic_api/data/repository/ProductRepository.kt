package com.example.winky_app.basic_api.data.repository

import com.example.winky_app.basic_api.data.model.ProductPostRequest
import com.example.winky_app.basic_api.data.model.ProductResponse
import com.example.winky_app.basic_api.data.network.ApiService

class ProductRepository(
    private val api: ApiService,
) {
    private val tokenBearer = "Bearer BKYVnXB_Na6lPaUiPbXLakTHjkumP5UNj89UTtLpZnLI_b3dIw"

    suspend fun fetchProduct(): ProductResponse {
        return api.getProducts(tokenBearer)
    }

    suspend fun createProduct(products: List<ProductPostRequest>): ProductResponse {
        return api.createProduct(tokenBearer, products)
    }
}