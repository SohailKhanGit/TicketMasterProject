package com.example.ticketmasterproject.di

import com.example.ticketmasterproject.ui.home.EventsListViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val viewModelModule = module {

    viewModel{ EventsListViewModel(get())}
 //   viewModel { EventDetailViewModel(get()) }
}