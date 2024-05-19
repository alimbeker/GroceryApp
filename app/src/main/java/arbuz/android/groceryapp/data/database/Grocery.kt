package arbuz.android.groceryapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_table")
data class Grocery(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val imageUrl: String,
    var quantityInCart: Int = 0
)
