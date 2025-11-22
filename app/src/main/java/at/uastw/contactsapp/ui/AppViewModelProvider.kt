package at.uastw.contactsapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import at.uastw.contactsapp.ContactsApplication

object AppViewModelProvider {

    val Factory = viewModelFactory {

        // Main ViewModel
        initializer {
            val contactsApplication = this[APPLICATION_KEY] as ContactsApplication
            ContactsViewModel(contactsApplication.contactRepository)
        }

        // Detailed ViewModel
        initializer {
            val contactsApplication = this[APPLICATION_KEY] as ContactsApplication
            ContactDetailViewModel(this.createSavedStateHandle(), contactsApplication.contactRepository)
        }

    }
}