package id.ariefpurnamamuharram.myfootballapplication.search

import id.ariefpurnamamuharram.myfootballapplication.model.SearchEvents

interface SearchEventsView {
    fun showSearchEvents(data: List<SearchEvents>)
}