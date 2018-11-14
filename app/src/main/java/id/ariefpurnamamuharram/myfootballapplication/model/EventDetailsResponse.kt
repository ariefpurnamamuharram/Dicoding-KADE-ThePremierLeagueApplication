package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class EventDetailsResponse (
        @SerializedName("events")
        val eventDetails: List<EventDetails> = ArrayList()
)