package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class EventDetails(
        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTime")
        var strTime: String? = null,

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = null,

        @SerializedName("intHomeScore")
        var intHomeScore: String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = null,

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = null,

        @SerializedName("intHomeShots")
        var intHomeShots: String? = null,

        @SerializedName("intAwayShots")
        var intAwayShots: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = null,

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = null,

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = null
)