package id.ariefpurnamamuharram.myfootballapplication.teams

import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeams

interface MainTeamsView {
    fun showListTeams(data: List<ListAllTeams>)
}