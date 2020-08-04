package com.example.ticketmasterproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmasterproject.R
import com.example.ticketmasterproject.database.DatabaseEvents
import com.example.ticketmasterproject.databinding.RowEventsBinding
import com.example.ticketmasterproject.domain.Events
import kotlinx.android.synthetic.main.row_events.view.*

class EventsAdapter(private val clickListener: EventsListener):RecyclerView.Adapter<EventsAdapter.EventsViewHolder>(){

    var results: List<Events> = emptyList()
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

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.results = results[position]

            it.resultsCallback = clickListener
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

class EventsListener(val clickListener:(id :String) -> Unit){
    fun onClick(event: DatabaseEvents) = clickListener(event.id)
}