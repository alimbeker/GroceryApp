package arbuz.android.groceryapp.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.databinding.FragmentCartBinding
import arbuz.android.groceryapp.presentation.adapter.GroceryAdapter
import arbuz.android.groceryapp.presentation.listener.GroceryItemClickListener
import arbuz.android.groceryapp.presentation.viewmodel.GroceryViewModel

class CartFragment : Fragment(), GroceryItemClickListener {

    private val viewModel: GroceryViewModel by viewModels()
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GroceryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = GroceryAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() {
        viewModel.groceries.observe(viewLifecycleOwner) { groceries ->
            val filteredGroceries = groceries.filter { it.quantityInCart > 0 }
            adapter.submitList(filteredGroceries)
        }
    }

    override fun onAddToCartClicked(grocery: Grocery) {
        viewModel.addToCart(grocery)
    }

    override fun onRemoveFromCartClicked(grocery: Grocery) {
        viewModel.removeFromCart(grocery)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
