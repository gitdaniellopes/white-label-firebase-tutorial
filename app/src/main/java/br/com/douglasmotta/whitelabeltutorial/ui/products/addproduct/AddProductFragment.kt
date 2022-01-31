package br.com.douglasmotta.whitelabeltutorial.ui.products.addproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.douglasmotta.whitelabeltutorial.databinding.AddProductFragmentBinding

class AddProductFragment : Fragment() {

    private var _binding: AddProductFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}