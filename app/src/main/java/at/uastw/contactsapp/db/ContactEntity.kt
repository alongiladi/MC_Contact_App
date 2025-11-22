package at.uastw.contactsapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Database specific UID. Set default to 0.
    val name: String,

    // The @ColumnInfo annotation in Room is used to customize the field in your Entity (table)
    @ColumnInfo("telephone_number")
    val telephoneNumber: String,

    val age: Int
)
