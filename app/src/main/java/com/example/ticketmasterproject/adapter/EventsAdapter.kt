package com.example.ticketmasterproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmasterproject.R
import com.example.ticketmasterproject.database.DatabaseEvents
import com.example.ticketmasterproject.databinding.RowEventsBinding
import com.example.ticketmasterproject.domain.Events
import java.util.*

//private val clickListener: EventsListener
class EventsAdapter(val clickListener: EventsListener):RecyclerView.Adapter<EventsAdapter.EventsViewHolder>(){

    var events: List<Events> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val withDataBinding:RowEventsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            EventsViewHolder.LAYOUT,
            parent,
            false
        )
        return EventsViewHolder(withDataBinding)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.events = events[position]

            it.eventsCallback = clickListener
        }
    }


    class EventsViewHolder(val viewDataBinding: RowEventsBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.row_events
        }
    }
}

class EventsListener(val clickListener:(id :Events) -> Unit){
    fun onClick(event: Events) = clickListener(event)
}