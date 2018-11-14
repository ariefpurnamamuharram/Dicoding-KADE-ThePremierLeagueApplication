package id.ariefpurnamamuharram.myfootballapplication.matchdetails

import id.ariefpurnamamuharram.myfootballapplication.model.AwayTeamBadge
import id.ariefpurnamamuharram.myfootballapplication.model.EventDetails
import id.ariefpurnamamuharram.myfootballapplication.model.HomeTeamBadge

interface MatchDetailsView {
    fun showMatchDetails(data: List<EventDetails>)
    fun showHomeTeamBadge(data: List<HomeTeamBadge>)
    fun showAwayTeamBadge(data: List<AwayTeamBadge>)
}