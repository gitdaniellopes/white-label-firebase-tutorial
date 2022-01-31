package br.com.douglasmotta.whitelabeltutorial.ui.products.addproduct

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import br.com.douglasmotta.whitelabeltutorial.databinding.AddProductFragmentBinding
import br.com.douglasmotta.whitelabeltutorial.util.CurrencyTextWatcher
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class AddProductFragment : BottomSheetDialogFragment() {

    private var _binding: AddProductFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddProductViewModel

    private var imageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
            binding.imageView2.setImageURI(uri)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
        setListeners()
    }

    private fun observers() {
        viewModel.imageUriErrorResId.observe(viewLifecycleOwner) { drawableResId ->
            binding.imageView2.setBackgroundResource(drawableResId)
        }
        viewModel.descriptionFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutDescription.setError(stringResId)
        }
        viewModel.priceFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutPrice.setError(stringResId)
        }
    }

    private fun TextInputLayout.setError(stringResId: Int?) {
        error = if (stringResId != null) {
            getString(stringResId)
        } else null
    }

    private fun setListeners() {
        binding.imageView2.setOnClickListener {
            chooseImage()
        }

        binding.buttonAddProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString().trim()
            val price = binding.inputPrice.text.toString().trim()

            viewModel.createProduct(description, price, imageUri)
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }
}