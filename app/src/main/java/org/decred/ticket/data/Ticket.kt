package org.decred.ticket.data


data class Ticket(
        val buytxid: String,
        val buytime: Int,
        val ticketprice: Double,
        val ticketfee: Double,
        val totalinvestment: Double,
        val status: String,
        val returntxid: String,
        val returntime: Int,
        val reward: Double,
        val feestakepool: Double,
        val returntotal: Double
)