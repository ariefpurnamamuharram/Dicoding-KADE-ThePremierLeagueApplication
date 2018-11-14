package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class TeamDetailsResponse(
        @SerializedName("teams")
        val teamDetails: List<TeamDetails> = ArrayList()
)