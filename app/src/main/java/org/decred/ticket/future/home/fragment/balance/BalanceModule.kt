package org.decred.ticket.future.home.fragment.balance

import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.util.DeliveryTicket

@Module
class BalanceModule {

    @Provides
    fun providesSettingsFragment(fragment: BalanceFragment): BalanceContract.View = fragment

    @Provides
    fun provideExchangePresenter(
            exchangeFragment: BalanceFragment, ticketInformation: TicketInformation, publishSubject: PublishSubject<DeliveryTicket>): BalanceContract.Presenter = BalancePresenter(exchangeFragment, ticketInformation, publishSubject)

}