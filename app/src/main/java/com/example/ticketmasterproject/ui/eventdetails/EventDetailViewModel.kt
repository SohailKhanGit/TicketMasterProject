package com.example.ticketmasterproject.ui.eventdetails

import android.app.Application
import androidx.lifecycle.*
import com.example.ticketmasterproject.domain.Events



class EventDetailViewModel(eventProperty: Events, app:Application):AndroidViewModel(app){

    // The internal MutableLiveData for the selected event
    private val _selectedProperty = MutableLiveData<Events>()

    // The external LiveData for the SelectedEvent
    val selectedProperty: LiveData<Events>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = eventProperty
    }


    /**
     * Simple ViewModel factory that provides the cocktailProperty and context to the ViewModel.
     */

    class EventDetailViewModelFactory (
        private val eventProperty: Events , private val application: Application
    ) :
        ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)) {
                return EventDetailViewModel(eventProperty, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }
}

