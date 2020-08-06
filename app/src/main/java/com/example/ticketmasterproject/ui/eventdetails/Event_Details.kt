package com.example.ticketmasterproject.ui.eventdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.ticketmasterproject.R
import com.example.ticketmasterproject.databinding.FragmentEventDetailsBinding
import com.example.ticketmasterproject.databinding.FragmentEventDetailsBindingImpl


/**
 * A simple [Fragment] subclass.
 * Use the [event_Details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Event_Details : Fragment() {


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val application = requireNotNull(activity).application

        val binding = FragmentEventDetailsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val eventProperty = Event_DetailsArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = EventDetailViewModel.EventDetailViewModelFactory(eventProperty, application)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(EventDetailViewModel::class.java)


        return binding.root
        //return inflater.inflate(R.layout.fragment_event__details, container, false)
    }

}
