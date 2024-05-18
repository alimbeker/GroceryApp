package arbuz.android.groceryapp.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import arbuz.android.groceryapp.databinding.FragmentHomeBinding
import arbuz.android.groceryapp.presentation.adapter.GroceryAdapter
import arbuz.android.groceryapp.presentation.viewmodel.GroceryViewModel

class HomeFragment : Fragment() {

    private val viewModel: GroceryViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GroceryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
        viewModel.loadGroceries() // Load the groceries when the view is created
    }

    private fun setupRecyclerView() {
        adapter = GroceryAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() {
        viewModel.groceries.observe(viewLifecycleOwner) { groceries ->
            adapter.submitList(groceries)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
