package org.decred.ticket.DAO

import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.data.Api
import org.decred.ticket.data.LiveTicketsRequest
import org.decred.ticket.data.Ticket
import org.decred.ticket.util.DeliveryTicket
import org.decred.ticket.util.StatusApplication
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class TicketInformation(
        val api: Api,
        val userPreference: UserPreference,
        val publishedSubjectTicket: PublishSubject<DeliveryTicket>) {


    var ticketReoganize: TicketReorganize? = null
    var error: Boolean = false
    fun onStart() {
        //TODO : Remove this url
        api.getTicketResult(userPreference.getWalletId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::success, this::error)

    }

    private fun success(tickets : List<Ticket>) {
        error = false
        if(tickets.isEmpty()) {
            ticketReoganize = TicketReorganize(tickets, 0, 0, 0.0, 0, 0.0, 0.0)
            publishedSubjectTicket.onNext(DeliveryTicket(StatusApplication.SUCCESS, ticketReoganize))
            return
        }
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

        val firebaseInstanceId = FirebaseInstanceId.getInstance().token
        val liveTickets = tickets
                .filter { firebaseInstanceId != null }
                .filter { ticket -> ticket.status == "live" }
                .map { ticket -> LiveTicketsRequest(ticket.buytxid, firebaseInstanceId !!) }
        saveTicketLive(liveTickets)

        tickets.filter { ticket -> ticket.status == "voted" }
                .map { ticket -> ticket.daysToVoted = TimeUnit.SECONDS.toDays(ticket.returntime.toLong() - ticket.buytime.toLong()).toInt() }

        tickets.filter { ticket -> ticket.status == "live" }
                .map { ticket -> ticket.daysInStake = TimeUnit.SECONDS.toDays(System.currentTimeMillis() / 1000 - ticket.buytime.toLong()).toInt() }

        val avgToVoted = tickets.filter { ticket -> ticket.status == "voted" }
                .map { ticket -> ticket.daysToVoted }
                .reduce { acc, i -> acc + i } / totalVoted

        ticketReoganize = TicketReorganize(tickets, totalVoted, totalLived, amountInLived, avgToVoted, totalRewards, totalFee)
        publishedSubjectTicket.onNext(DeliveryTicket(StatusApplication.SUCCESS, ticketReoganize))
    }

    private fun saveTicketLive(liveTickets: List<LiveTicketsRequest>) {
        if (liveTickets.isEmpty()) return
        api.saveTicketedLive(liveTicketsRequest = liveTickets)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success ->
                    Timber.i(success.message)
                }, { throwable -> Timber.e(throwable) })
    }

    private fun error(throwable: Throwable) {
        publishedSubjectTicket.onNext(DeliveryTicket(StatusApplication.ERROR))
        error = true
        Timber.e(throwable)
    }

    fun destroy() {
        ticketReoganize = null
        error = false
    }

}


data class TicketReorganize(
        val tickets: List<Ticket>,
        val totalTicketsVoted: Int,
        val totalTicketsLived: Int,
        val amountInLive: Double,
        val avgToVoted: Int,
        val totalReward: Double,
        val totalFee: Double)