package id.ariefpurnamamuharram.myfootballapplication.search

import id.ariefpurnamamuharram.myfootballapplication.model.TeamByName

interface SearchTeamsView {
    fun showSearchTeams(data: List<TeamByName>)
}