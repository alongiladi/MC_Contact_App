package at.uastw.contactsapp

import android.app.Application
import at.uastw.contactsapp.db.ContactsDatabase
import at.uastw.contactsapp.data.ContactRepository

// must extend from the Application()
class ContactsApplication : Application() {

    // create the database and dao object
    // by lazy, will be called when necessary, and only once
    val contactRepository by lazy {

        val contactsDao = ContactsDatabase.getDatabase(this).contactsDao()
        ContactRepository(contactsDao)
    }
}