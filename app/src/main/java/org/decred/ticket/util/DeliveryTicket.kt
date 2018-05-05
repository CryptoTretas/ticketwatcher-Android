package org.decred.ticket.util

import org.decred.ticket.DAO.TicketReorganize

open class DeliveryTicket(val status: StatusApplication, val tickets: TicketReorganize? = null)

enum class StatusApplication {
    SUCCESS,
    RELOAD,
    ERROR,
}