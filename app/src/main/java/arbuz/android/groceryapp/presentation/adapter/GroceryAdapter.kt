package arbuz.android.groceryapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.databinding.ItemLayoutBinding
import arbuz.android.groceryapp.presentation.listener.GroceryItemClickListener
import com.bumptech.glide.Glide


class GroceryAdapter(private val listener: GroceryItemClickListener) : ListAdapter<Grocery, GroceryAdapter.ViewHolder>(GroceryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grocery = getItem(position)
        holder.bind(grocery)
    }

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
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
}

class GroceryDiffCallback : DiffUtil.ItemCallback<Grocery>() {
    override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
        return oldItem == newItem
    }
}


