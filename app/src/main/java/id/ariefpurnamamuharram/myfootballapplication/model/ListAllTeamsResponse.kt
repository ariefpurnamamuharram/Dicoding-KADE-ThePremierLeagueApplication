package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class ListAllTeamsResponse(
        @SerializedName("teams")
        val listAllTeams: List<ListAllTeams> = ArrayList()
)