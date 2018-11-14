package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class SearchEventsResponse(
        @SerializedName("event")
        val searchMatch: List<SearchEvents> = ArrayList()
)