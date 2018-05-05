package org.decred.ticket.future.home.fragment.balance

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.DAO.TicketReorganize
import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.home.HomeContract
import org.decred.ticket.future.home.fragment.extract.ExtractContract
import org.decred.ticket.util.DeliveryTicket
import org.decred.ticket.util.StatusApplication
import timber.log.Timber
import javax.inject.Inject

class BalancePresenter @Inject constructor(
        private val view: BalanceContract.View,
        private val ticketInformation: TicketInformation,
        private val publishSubject: PublishSubject<DeliveryTicket>) : BalanceContract.Presenter {


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onStart() {
        if (ticketInformation.ticketReoganize != null) {
            success(ticketInformation.ticketReoganize)
        }
        compositeDisposable.add(
                publishSubject
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ t: DeliveryTicket ->
                            when (t.status) {
                                StatusApplication.SUCCESS -> this.success(t.tickets)
                                StatusApplication.RELOAD -> this.reloadView()
                                StatusApplication.ERROR -> this.error(kotlin.Throwable("Error"))
                            }
                            if (t.status == StatusApplication.SUCCESS) {

                            }
                        }, this::error)
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun reloadView() {
        view.onReload()
    }

    private fun success(ticketReorganize: TicketReorganize?) {
        view.onSuccess(ticketReorganize!!)
    }

    private fun error(throwable: Throwable) {
        Timber.e(throwable)
        view.onError()
    }
}