package com.example.ticketmasterproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Events(
    var name:String,
    var type: String,
    var id: String
   // var url: String,
    //var dates: Dates,
   // var promoter: Promoter,
   // var info: String
    //var priceRanges: List<PriceRange>,

  //  var _embedded:@RawValue EmbeddedVenues
):Parcelable