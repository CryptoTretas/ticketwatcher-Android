package org.decred.ticket.future.home.fragment.extract

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.decred.ticket.R
import org.decred.ticket.data.Ticket
import java.util.*


class ExtractRecycleView(
        private val context: Context,
        private val tickets: List<Ticket>
) : RecyclerView.Adapter<ExtractRecycleView.AdapterViewHolder>() {

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val ticket = tickets[position]
        holder.let { entity ->
            entity.txid.text = ticket.buytxid
            entity.price.text = "DCR " + ticket.ticketprice
            entity.status.text = ticket.status
            entity.date.text = Date(ticket.buytime.toLong()).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.adapter_recycle_view, parent, false))
    }


    inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txid: TextView = itemView.findViewById(R.id.txId)
        var price: TextView = itemView.findViewById(R.id.price)
        var status: TextView = itemView.findViewById(R.id.status)
        var date: TextView = itemView.findViewById(R.id.date)
    }
}