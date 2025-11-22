package at.uastw.contactsapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.uastw.contactsapp.data.Contact
import at.uastw.contactsapp.data.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class  ContactDetailUiState(
    val contact: Contact
)

class ContactDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ContactRepository ) : ViewModel()
{
    // read the contactId and add to the nav controller
    private val contactId: Int = savedStateHandle["contactId"] ?: 0

    // mutable one, stay in private
    private val _contactDetailUiState = MutableStateFlow(ContactDetailUiState(Contact(0, "", "", 0)))
    // public one, and not mutable
    val contactDetailUiState = _contactDetailUiState.asStateFlow()

    init{

        viewModelScope.launch {
            val contact = repository.findContactById(contactId)
            _contactDetailUiState.update {
                it.copy(contact = contact)
            }
        }
    }
}