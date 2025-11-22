package at.uastw.contactsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.uastw.contactsapp.data.Contact
import at.uastw.contactsapp.data.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ContactsUiState(
    val contacts: List<Contact>,
    val selectedCardIndex: Int?
)

// class ContactsViewModel(val repository: ContactRepository = ContactRepository()): ViewModel() {
class ContactsViewModel(val repository: ContactRepository): ViewModel() {

    // delete the following
//    private val _contactsUiState = MutableStateFlow(ContactsUiState(emptyList(), null))
//    val contactsUiState = _contactsUiState.asStateFlow()
    // convert the Flow(repository.contacts) to a StateFlow by calling stateIn()
    val contactsUiState = repository.contacts.stateIn(
        viewModelScope,
        // SharingStarted.WhileSubscribed(5000) controls when the flow starts and stops collecting. 5 sec avoids restarting the flow immediately if a UI rotates or briefly detaches.
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    // delete the following
//    init {
//        // Here we are launching a coroutine
//        // coroutines are code that runs concurrently
//        // we need it for the collect function
//        // since it is a suspend function
//        // we need to use the collect function instead of
//        // collectAsStateWithLifecycle since we are in a ViewModel
//        // this code loads the initial data from the repository
//        viewModelScope.launch {
//            repository.contacts.collect { data ->
//                // the collect function is called whenever the
//                // contacts data changes
//                _contactsUiState.update {
//                    it.copy(contacts = data)
//                }
//            }
//        }
//    }

    fun onAddButtonClicked() {
        viewModelScope.launch{
            repository.addRandomContact()
        }
    }

    // Delete the following
//    fun onCardClick(index: Int) {
//        _contactsUiState.update {
//            it.copy(selectedCardIndex = index)
//        }
//    }
}