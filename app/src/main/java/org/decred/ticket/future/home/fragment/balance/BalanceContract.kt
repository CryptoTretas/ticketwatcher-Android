package org.decred.ticket.future.home.fragment.balance

import org.decred.ticket.DAO.TicketReorganize

interface BalanceContract {
    interface View{
        fun onSuccess(ticketReorganize: TicketReorganize)
        fun onError()
        fun onReload()
    }
    interface Presenter{
        fun onStart()
        fun onDestroy()
    }
}