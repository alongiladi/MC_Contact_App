package at.uastw.contactsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Version our db in app development.
@Database(
    entities = [ContactEntity::class], // This database will include one entity/table: ContactEntity.
    version = 1
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        // @Volatile ensures that changes made to Instance by one thread are visible to all threads immediately.
        @Volatile
        private var Instance: ContactsDatabase? = null

        // Provides a global access point to your database.
        fun getDatabase(context: Context): ContactsDatabase {

            // If the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {

                // build database
                val instance = Room
                    .databaseBuilder(context, ContactsDatabase::class.java, "contact_database")
                    .build()

                // Stores and returns the newly created database instance.
                Instance = instance
                return instance
            }
        }
    }
}