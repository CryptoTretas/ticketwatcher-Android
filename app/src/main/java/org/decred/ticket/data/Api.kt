package org.decred.ticket.data

import io.reactivex.Single
import retrofit2.http.*

interface Api {
    @GET("match/{wallet_id}")
    fun getTicketResult(@Path("wallet_id") walletId: String): Single<List<Ticket>>

    @POST
    fun saveTicketedLive(@Url url: String = "https://us-central1-ticketwatcher-e1f9d.cloudfunctions.net/api/saveLiveToken", @Body liveTicketsRequest: List<LiveTicketsRequest>): Single<Response>

}