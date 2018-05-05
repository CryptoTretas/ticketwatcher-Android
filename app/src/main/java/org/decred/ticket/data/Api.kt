package org.decred.ticket.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("match/{wallet_id}")
    fun getTicketResult(@Path("wallet_id") walletId: String): Single<List<Ticket>>

}