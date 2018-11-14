package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class TeamByNameResponse(
        @SerializedName("teams")
        val listTeamByName: List<TeamByName> = ArrayList()
)