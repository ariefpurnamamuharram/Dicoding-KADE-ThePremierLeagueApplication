package id.ariefpurnamamuharram.myfootballapplication.teamdetails

import id.ariefpurnamamuharram.myfootballapplication.model.TeamDetails

interface TeamDetailsView {
    fun showTeamDetails(data: List<TeamDetails>)
}