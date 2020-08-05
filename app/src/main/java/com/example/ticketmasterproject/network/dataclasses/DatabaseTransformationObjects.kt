package com.example.ticketmasterproject.network.dataclasses

import com.example.ticketmasterproject.database.DatabaseEvents
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkEmbeddedContainer(var _embedded:NetworkEventsContainer)
@JsonClass(generateAdapter = true)
data class NetworkEventsContainer(var events :List<NetworkEventDetails> )


@JsonClass(generateAdapter = true)
data class NetworkEventDetails(
    var name:String
  //  var type: String,
   // var id: String,
   // var url: String,
   // var dates: Dates,
   // var promoter: Promoter,
   // var info: String,
   // var priceRanges: List<PriceRange>,
   // var embeddedvenuesContainer: List<Venues>
)


fun NetworkEmbeddedContainer.asDatabaseModel():List<DatabaseEvents>{
    return _embedded.events.map {
        DatabaseEvents(
            name = it.name
          //  type = it.type,
          //  id = it.id,
           // url = it.url,
          //  dates = it.dates,
           // promoter = it.promoter,
          //  info = it.info
         //   priceRanges = it.priceRanges,
          //  embeddedvenuesContainer = it.embeddedvenuesContainer

        )
    }
}









