package br.com.douglasmotta.whitelabeltutorial.data

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

// O repositorio só existe, por que temos os nossos casos de uso
class ProductRepository(
    private val dataSource: ProductDataSource
) {
    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun uploadProductImage(imageUri: Uri): String = dataSource.uploadProductImage(imageUri)

    suspend fun createProduct(product: Product): Product = dataSource.createProduct(product)
}