package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class LastMatch(
        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayteam: String? = null,

        @SerializedName("intHomeScore")
        var intHomeScore: String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore: String? = null,

        @SerializedName("strTime")
        var strTime: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null
)