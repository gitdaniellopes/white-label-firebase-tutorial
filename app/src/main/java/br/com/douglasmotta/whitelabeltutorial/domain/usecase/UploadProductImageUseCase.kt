package br.com.douglasmotta.whitelabeltutorial.domain.usecase

import android.net.Uri

interface UploadProductImageUseCase {

    //só posso utulizar o operator se o nome da minha função tiver o nome de incoke

    suspend operator fun invoke(imageUri: Uri): String
}