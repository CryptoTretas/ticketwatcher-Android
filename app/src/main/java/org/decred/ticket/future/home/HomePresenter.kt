package org.decred.ticket.future.home

import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.DAO.UserPreference
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private val view: HomeContract.View,
        private val ticketInformation: TicketInformation) : HomeContract.Presenter {


    override fun onStart() {
        ticketInformation.onStart()
    }

    override fun onDestroy() {
        ticketInformation.destroy()
    }
}