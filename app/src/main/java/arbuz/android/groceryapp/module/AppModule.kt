package arbuz.android.groceryapp.module

import android.content.Context
import androidx.room.Room
import arbuz.android.groceryapp.data.database.GroceryDao
import arbuz.android.groceryapp.data.database.GroceryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GroceryDatabase {
        return Room.databaseBuilder(
            appContext,
            GroceryDatabase::class.java,
            "grocery_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideGroceryDao(database: GroceryDatabase): GroceryDao {
        return database.groceryDao()
    }
}
