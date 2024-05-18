package arbuz.android.groceryapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import arbuz.android.groceryapp.data.database.Grocery
import androidx.recyclerview.widget.DiffUtil
import arbuz.android.groceryapp.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide


class GroceryAdapter :
    ListAdapter<Grocery, GroceryAdapter.GroceryViewHolder>(GroceryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroceryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val grocery = getItem(position)
        holder.bind(grocery)
    }

    class GroceryViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(grocery: Grocery) {
            binding.name.text = grocery.name
            binding.price.text = grocery.price.toString()
            Glide.with(binding.image.context)
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


