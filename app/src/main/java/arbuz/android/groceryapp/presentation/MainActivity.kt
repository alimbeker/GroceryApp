package arbuz.android.groceryapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import arbuz.android.groceryapp.R
import arbuz.android.groceryapp.databinding.ActivityMainBinding
import arbuz.android.groceryapp.presentation.viewmodel.GroceryViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    private val viewModel: GroceryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationView()
        observeData()
    }

    private fun observeData() {
        viewModel.groceries.observe(this) { groceries ->
            val itemCount = groceries.filter { it.quantityInCart > 0 }.size
            updateCartBadge(itemCount)
        }
    }

    private fun setupBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeScreen -> navController.navigate(R.id.homeFragment)
                R.id.cartScreen -> navController.navigate(R.id.cartFragment)
            }
            true
        }
    }

    private fun updateCartBadge(itemCount: Int) {
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val badge = bottomNavView.getOrCreateBadge(R.id.cartScreen)
        if (itemCount > 0) {
            badge.isVisible = true
            badge.number = itemCount
        } else {
            badge.isVisible = false
        }
    }
}
