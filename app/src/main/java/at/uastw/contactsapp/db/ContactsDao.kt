package at.uastw.contactsapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    // Insert
    // @Upsert: if exist then update, otherwise insert
    // One-shot write query -> suspend fun
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contactEntity: ContactEntity)

    // Update
    // One-shot write query -> suspend fun
    @Update
    suspend fun updateContact(contactEntity: ContactEntity)

    // Delete
    // One-shot write query -> suspend fun
    @Delete
    suspend fun deleteContact(contactEntity: ContactEntity)

    // Queries
    // One-shot read query -> suspend fun
    // Flow is a data structure that will notify us about there is a change in the table.
    // In practice, the flow will emit a new list of contact
    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun findContactById(id: Int): ContactEntity

    // Observable read queries -> no suspend keyword
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE name = :contactName")
    fun findContactsWithName(contactName: String): Flow<List<ContactEntity>>
}