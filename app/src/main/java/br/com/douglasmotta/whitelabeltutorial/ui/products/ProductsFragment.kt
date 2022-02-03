package br.com.douglasmotta.whitelabeltutorial.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()
    private val productsAdapter by lazy { ProductsAdapter() }
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
        observers()
        viewModel.getProducts()
    }

    private fun setupRecycleView() {
        binding.recyclerProducts.run {
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    private fun observers() {
        viewModel.productsData.observe(viewLifecycleOwner) { products ->
            productsAdapter.submitList(products)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}