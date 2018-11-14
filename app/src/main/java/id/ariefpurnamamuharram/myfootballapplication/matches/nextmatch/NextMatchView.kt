package id.ariefpurnamamuharram.myfootballapplication.matches.nextmatch

import id.ariefpurnamamuharram.myfootballapplication.model.NextMatch

interface NextMatchView {
    fun showNextMatchList(data: List<NextMatch>)
}