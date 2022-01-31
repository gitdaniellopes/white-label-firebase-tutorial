package br.com.douglasmotta.whitelabeltutorial.ui.products.addproduct

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import br.com.douglasmotta.whitelabeltutorial.databinding.AddProductFragmentBinding
import br.com.douglasmotta.whitelabeltutorial.util.CurrencyTextWatcher
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
        setListeners()
    }

    private fun setListeners() {
        binding.imageView2.setOnClickListener {
            chooseImage()
        }

        binding.buttonAddProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString().trim()
            val price = binding.inputPrice.text.toString().trim()
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }
}