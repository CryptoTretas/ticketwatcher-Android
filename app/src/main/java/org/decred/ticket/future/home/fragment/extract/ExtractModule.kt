package org.decred.ticket.future.home.fragment.extract

import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.future.home.fragment.balance.ExtractFragment
import org.decred.ticket.util.DeliveryTicket

@Module
class ExtractModule {

    @Provides
    fun providesSettingsFragment(fragment: ExtractFragment): ExtractContract.View = fragment

    @Provides
    fun provideExchangePresenter(
            exchangeFragment: ExtractFragment,
            ticketInformation: TicketInformation,
            publishedSubjectTicket: PublishSubject<DeliveryTicket>
    ): ExtractContract.Presenter = ExtractPresenter(exchangeFragment, ticketInformation, publishedSubjectTicket)

}