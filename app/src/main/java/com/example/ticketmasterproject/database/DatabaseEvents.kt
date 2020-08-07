package com.example.ticketmasterproject.database

import android.net.Network
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ticketmasterproject.domain.*
import com.example.ticketmasterproject.network.dataclasses.*
import com.example.ticketmasterproject.network.dataclasses.Dates
import com.example.ticketmasterproject.network.dataclasses.Location
import com.example.ticketmasterproject.network.dataclasses.PriceRange
import com.example.ticketmasterproject.network.dataclasses.Promoter
import com.example.ticketmasterproject.network.dataclasses.Start
import com.example.ticketmasterproject.network.dataclasses.Venues


/*@Entity
data class DatabaseEventsContainer(
    var eventContainer :List<NetworkEventDetails>
)*/

@Entity
data class DatabaseEvents(
    var name:String,
    var type: String,
    @PrimaryKey
    var id: String,
    var images:List<Image>,
  //  var url: String,
  //  var dates: Dates,
  //  var promoter: Promoter,
  //  var info: String
  //  var priceRanges: List<PriceRange>,
   // var _embedded: EmbeddedVenues
    var info: String?
)


fun List<DatabaseEvents>.asDomainModel():List<Events>{
    return map {
        Events(
            name = it.name,
            type = it.type,
            id = it.id,
            images = it.images,
            info = it.info
                    //  url = it.url,
                    //  dates = it.dates,
                    //promoter = it.promoter,
                    // info = it.info
                    //priceRanges = it.priceRanges,
                    //_embedded = it._embedded

        )
    }
}

//
//@Entity
//data class DatabaseDates (
//    var start: Start,
//    var timezone: String)
//
//@Entity
//data class DatabaseStart(
//    var localData: String,
//    var localTime: String)
//
//@Entity
//data class DatabasePriceRange (
//    var type: String,
//    var currency: String,
//    var min:String,
//    var max:String
//)
//
//@Entity
//data class DatabasePromoter (
//    var id:String,
//    var name: String,
//    var description: String
//)
//
//@Entity
//data class DatabaseVenues (
//    var name: String,
//    var venueUrl: String,
//    var location: Location
//)
//
//@Entity
//data class DatabaseLocation(
//    var longitude: String,
//    var latitude: String
//)
//
