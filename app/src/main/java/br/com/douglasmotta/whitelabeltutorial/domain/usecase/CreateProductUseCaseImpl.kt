package br.com.douglasmotta.whitelabeltutorial.domain.usecase

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.data.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import java.util.*

class CreateProductUseCaseImpl(
    private val uploadProductImageUseCase: UploadProductImageUseCase,
    private val repository: ProductRepository
) : CreateProductUseCase {

    override suspend fun invoke(description: String, price: Double, imageUri: Uri): Product {
        return try {
            val imageUrl = uploadProductImageUseCase(imageUri)
            val product = Product(UUID.randomUUID().toString(), description, price, imageUrl)
            repository.createProduct(product)
        } catch (ex: Exception) {
            throw ex
        }
    }
}