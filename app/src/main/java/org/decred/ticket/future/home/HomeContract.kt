package org.decred.ticket.future.home

interface HomeContract{
    interface View
    interface Presenter {
        fun onStart()
        fun onDestroy()
    }
}