package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import id.ariefpurnamamuharram.myfootballapplication.model.LastMatch

interface LastMatchView {
    fun showLastMatchList(data: List<LastMatch>)
}