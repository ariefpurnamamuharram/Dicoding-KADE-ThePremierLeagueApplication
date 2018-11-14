package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class AwayTeamBadgeResponse (
        @SerializedName("teams")
        val awayTeamBadge: List<AwayTeamBadge> = ArrayList()
)