package org.decred.ticket.DAO

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.data.Api
import org.decred.ticket.data.Ticket
import org.decred.ticket.util.DeliveryTicket
import timber.log.Timber


class TicketInformation(
        val api: Api,
        val userPreference: UserPreference,
        val publishedSubjectTicket: PublishSubject<DeliveryTicket>) {


    fun onStart() {
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

        val ticketReoganize = TicketReorganize(tickets, totalRewards, totalFee)
        publishedSubjectTicket.onNext(DeliveryTicket(ticketReoganize))
    }


    private fun error(throwable: Throwable) {
        Timber.e(throwable)
    }

}


data class TicketReorganize(
        val tickets: List<Ticket>,
        val totalReward: Double,
        val totalFee: Double)