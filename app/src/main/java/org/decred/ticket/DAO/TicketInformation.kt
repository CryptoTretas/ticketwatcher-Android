package org.decred.ticket.DAO

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.data.Api
import org.decred.ticket.data.Ticket
import org.decred.ticket.util.DeliveryTicket
import org.decred.ticket.util.StatusApplication
import timber.log.Timber
import java.util.concurrent.TimeUnit


class TicketInformation(
        val api: Api,
        val userPreference: UserPreference,
        val publishedSubjectTicket: PublishSubject<DeliveryTicket>) {


    lateinit var ticketReoganize: TicketReorganize

    fun onStart() {
        //TODO : Remove this url
        userPreference.saveWalletId("DsT1mCSfjHTpao82FTvwwAdbAJDxELsNEqb")
        api.getTicketResult(userPreference.getWalletId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::success, this::error)

    }

    private fun success(tickets: List<Ticket>) {
        val totalRewards = tickets
                .map { ticket -> ticket.reward }
                .reduce { acc, d -> acc + d }

        val totalFee = tickets
                .map { ticket -> ticket.ticketfee }
                .reduce { acc, d -> acc + d }

        val totalVoted = tickets
                .filter { ticket -> ticket.status == "voted" }
                .size

        val totalLived = tickets
                .filter { ticket -> ticket.status == "live" }
                .size

        val amountInLived = tickets
                .filter { ticket -> ticket.status == "live" }
                .map { ticket -> ticket.totalinvestment }
                .reduce { acc, d -> acc + d }

        tickets.filter { ticket -> ticket.status == "voted" }.map { ticket -> ticket.daysToVoted = TimeUnit.SECONDS.toDays(ticket.returntime.toLong() - ticket.buytime.toLong()).toInt() }
        tickets.filter { ticket -> ticket.status == "live" }.map { ticket -> ticket.daysInStake = TimeUnit.SECONDS.toDays(System.currentTimeMillis() / 1000 - ticket.buytime.toLong()).toInt() }

        val avgToVoted = tickets.filter { ticket -> ticket.status == "voted" }
                .map { ticket -> ticket.daysToVoted }
                .reduce { acc, i -> acc + i } / totalVoted

        ticketReoganize = TicketReorganize(tickets, totalVoted, totalLived, amountInLived, avgToVoted, totalRewards, totalFee)
        publishedSubjectTicket.onNext(DeliveryTicket(StatusApplication.SUCCESS, ticketReoganize))
    }


    private fun error(throwable: Throwable) {
        publishedSubjectTicket.onNext(DeliveryTicket(StatusApplication.ERROR))
        Timber.e(throwable)
    }

}


data class TicketReorganize(
        val tickets: List<Ticket>,
        val totalTicketsVoted: Int,
        val totalTicketsLived: Int,
        val amountInLive: Double,
        val avgToLVoted: Int,
        val totalReward: Double,
        val totalFee: Double)