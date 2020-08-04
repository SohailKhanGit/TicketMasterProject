package com.example.ticketmasterproject.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.ticketmasterproject.database.getDatabase
import com.example.ticketmasterproject.domain.Events
import com.example.ticketmasterproject.repository.EventsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class EventsListViewModel(application: Application):AndroidViewModel(application) {

    private val eventsRepository = EventsRepository(getDatabase(application))

    val eventsList = eventsRepository.results


    //This is the job for all coroutines started by this ViewModel
    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //Event triggered for network error.

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() =_eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    //init{} is called immediately when this ViewModel is created.
    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                eventsRepository.refreshEvents()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }catch (newtworkError: IOException){
                if(eventsList.value.isNullOrEmpty())
                    _eventNetworkError.value  = true
            }
        }
    }

    //reset the network error flag
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    //cancel all coroutines when the viewmodel is cleared
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(EventsListViewModel::class.java)){
                @Suppress("UNCHECKED CAST")
                return  EventsListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }

    }
    //for display data in second fragment
    // LiveData to handle navigation to the selected Event
    private val _navigateToSelectedProperty = MutableLiveData<Events>()
    val navigateToSelectedProperty: LiveData<Events>
        get() = _navigateToSelectedProperty


    fun displayPropertyDetails(eventsProperty: Events) {
        _navigateToSelectedProperty.value = eventsProperty
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}