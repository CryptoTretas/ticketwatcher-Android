package org.decred.ticket.future.home.fragment.extract

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.DAO.TicketReorganize
import org.decred.ticket.util.DeliveryTicket
import org.decred.ticket.util.StatusApplication
import timber.log.Timber
import javax.inject.Inject

class ExtractPresenter @Inject constructor(
        private val view: ExtractContract.View,
        private val ticketInformation: TicketInformation,
        private val publishSubject: PublishSubject<DeliveryTicket>) : ExtractContract.Presenter {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onStart() {
        if (ticketInformation.ticketReoganize != null) {
            success(ticketInformation.ticketReoganize)
        } else if (ticketInformation.error) {
            view.onError()
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
        view.onSuccess(ticketsList = ticketReorganize!!.tickets)
    }

    private fun error(throwable: Throwable) {
        Timber.e(throwable)
        view.onError()
    }
}