package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class SearchEvents(
        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayteam: String? = null,

        @SerializedName("strEvent")
        var strEvent: String? = null,

        @SerializedName("intHomeScore")
        var intHomeScore: String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTime")
        var strTime: String? = null
)