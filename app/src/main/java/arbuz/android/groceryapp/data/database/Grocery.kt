package arbuz.android.groceryapp.data.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "grocery_table",
    indices = [Index(value = ["id"], unique = true)]
)
data class Grocery(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    var quantityInCart: Int = 0,

)
