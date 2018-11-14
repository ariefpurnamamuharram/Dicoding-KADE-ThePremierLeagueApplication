package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class HomeTeamBadgeResponse (
        @SerializedName("teams")
        val homeTeamBadge: List<HomeTeamBadge> = ArrayList()
)