package com.example.ticketmasterproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmasterproject.R
import com.example.ticketmasterproject.adapter.EventsAdapter
import com.example.ticketmasterproject.databinding.FragmentHomeBindingImpl
import com.example.ticketmasterproject.domain.Events


class HomeFragment : Fragment() {



    private var viewModelAdapter: EventsAdapter? = null
    /**LAZY
     *One way to delay creation of the viewmodel until an appropriate lifecycle method is to use Lazy
     *This requires that viewmodel not be referenced before onActivityCreated, which we do in this fragment
     */
    private val eventsListViewModel: EventsListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, EventsListViewModel.Factory(activity.application))
            .get(EventsListViewModel::class.java)
    }


    /**Method for displaying a toast error message for network errors.
     *
     */

    private fun onNetworkError() {
        if(!eventsListViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity,"Network Error", Toast.LENGTH_LONG).show()
            eventsListViewModel.onNetworkErrorShown()
        }
    }



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

         val binding: FragmentHomeBindingImpl = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

       binding.setLifecycleOwner (viewLifecycleOwner)
       binding.viewmodel = eventsListViewModel
        viewModelAdapter = EventsAdapter()
/*//       viewModelAdapter = EventsAdapter(EventsListener{
//          eventsListViewModel.displayPropertyDetails(it)
//       })*/
         binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
         }

       //Observer for the network error
       eventsListViewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
          if(isNetworkError) onNetworkError()
       })

///*//       eventsListViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
////          if ( null != it ) {
////             this.findNavController().navigate(Directions.actionFirstFragmentToSecondFragment(it))
////             // Tell the ViewModel we've made the navigate call to prevent multiple navigation
////             viewModel.displayPropertyDetailsComplete()
////          }
////       })*/

        setHasOptionsMenu(true)
        return binding.root
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsListViewModel.eventsList.observe(viewLifecycleOwner, Observer<List<Events>> { events ->
            events?.apply {
                viewModelAdapter?.events =events
            }
        })


    }

}