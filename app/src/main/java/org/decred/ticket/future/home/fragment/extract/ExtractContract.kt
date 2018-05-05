package org.decred.ticket.future.home.fragment.extract

import org.decred.ticket.data.Ticket

interface ExtractContract {
    interface View {
        fun onSuccess(ticketsList: List<Ticket>)
        fun onError()
        fun onReload()
    }

    interface Presenter{
        fun onStart()
        fun onDestroy()
    }
}