package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class TeamByName(
        @SerializedName("idTeam")
        var idTeam: String? = null,

        @SerializedName("strTeam")
        var strTeam: String? = null,

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = null
)