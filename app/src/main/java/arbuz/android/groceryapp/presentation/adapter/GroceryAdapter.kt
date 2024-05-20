package arbuz.android.groceryapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.databinding.CartItemLayoutBinding
import arbuz.android.groceryapp.databinding.ItemLayoutBinding
import arbuz.android.groceryapp.presentation.listener.GroceryItemClickListener
import com.bumptech.glide.Glide


class GroceryAdapter(private val viewType: Int, private val listener: GroceryItemClickListener) :
    ListAdapter<Grocery, RecyclerView.ViewHolder>(GroceryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HOME.ordinal -> {
                val binding =
                    ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeViewHolder(binding)
            }

            ViewType.CART.ordinal -> {
                val binding = CartItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CartViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val grocery = getItem(position)
        when (holder) {
            is HomeViewHolder -> holder.bind(grocery)
            is CartViewHolder -> holder.bind(grocery)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    inner class HomeViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonAddToCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val grocery = getItem(position)
                    listener.onAddToCartClicked(grocery)
                }
            }

            binding.buttonRemoveFromCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val grocery = getItem(position)
                    listener.onRemoveFromCartClicked(grocery)
                }
            }
        }

        fun bind(grocery: Grocery) {
            binding.name.text = grocery.name
            binding.price.text = "1kg, ${grocery.price}$"
            binding.quantityOfProduct.text = grocery.quantityInCart.toString()

            // Load image using Glide
            Glide.with(binding.root.context)
                .load(grocery.imageUrl)
                .into(binding.image)
        }
    }

    inner class CartViewHolder(private val binding: CartItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonAddToCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val grocery = getItem(position)
                    listener.onAddToCartClicked(grocery)
                }
            }

            binding.buttonRemoveFromCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val grocery = getItem(position)
                    listener.onRemoveFromCartClicked(grocery)
                }
            }
        }

        fun bind(grocery: Grocery) {
            binding.name.text = grocery.name
            val totalPrice = if (grocery.quantityInCart > 0) grocery.price * grocery.quantityInCart else grocery.price
            val totalWeight = if (grocery.quantityInCart > 0) grocery.quantityInCart else 1
            binding.price.text = "${totalWeight}kg, ${String.format("%.2f", totalPrice)}$"
            binding.quantityOfProduct.text = grocery.quantityInCart.toString()

            // Load image using Glide
            Glide.with(binding.root.context)
                .load(grocery.imageUrl)
                .into(binding.image)
        }
    }
}

class GroceryDiffCallback : DiffUtil.ItemCallback<Grocery>() {
    override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
        return oldItem == newItem
    }
}
