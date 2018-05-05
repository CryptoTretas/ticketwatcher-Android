package org.decred.ticket.util

import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Singleton


@Module
@Singleton
class DeliveryModule {

    @Provides
    @Singleton
    fun providesUpdateBalanceEvent(): PublishSubject<DeliveryTicket> = PublishSubject.create()


}
