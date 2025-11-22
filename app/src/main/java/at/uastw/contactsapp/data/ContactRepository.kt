package at.uastw.contactsapp.data

import at.uastw.contactsapp.db.ContactEntity
import at.uastw.contactsapp.db.ContactsDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.collections.plus

// Fake Database
class ContactRepository(private val contactsDao: ContactsDao) {

    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )

    // delete the following
//    private val _contacts = MutableStateFlow(createContacts())
//    val contacts = _contacts.asStateFlow()
    // When getting the contacts from the database, map the field value (database) to the Contact object
    val contacts = contactsDao.getAllContacts().map { contactList ->
        contactList.map { entity ->
            Contact(
                entity.id, entity.name,
                entity.telephoneNumber,
                entity.age
            )
        }
    }


    // delete the following
//    fun createContacts(): List<Contact> {
//        val contacts = (1..20).map{
//            Contact(
//                "${names.random()} $it",
//                "+43 123455$it",
//                25 + it
//            )
//        }
//
//        return contacts
//    }

    // update the following
//    fun addRandomContact() {
//        _contacts.update { oldList ->
//            oldList + Contact(names.random(), "+4357894", 45)
//        }
//    }
    suspend fun addRandomContact() {
        contactsDao.addContact(
            ContactEntity(
                0, names.random(),
                "+4338383", 43
            )
        )
    }

    suspend fun findContactById(contactId: Int): Contact {

        val contactEntity = contactsDao.findContactById(contactId)

        return Contact( contactEntity.id, contactEntity.name, contactEntity.telephoneNumber, contactEntity.age)
    }
}