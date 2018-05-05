package org.decred.ticket.future.home.fragment.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_balance.view.*
import org.decred.ticket.DAO.TicketReorganize
import org.decred.ticket.R
import javax.inject.Inject

class BalanceFragment : DaggerFragment(), BalanceContract.View {

    @Inject
    lateinit var presenter: BalanceContract.Presenter

    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.fragment_balance, container, false)
        Glide.with(activity!!.applicationContext)
                .load(R.mipmap.stakey)
                .into(fragmentView.progress_bar)
        presenter.onStart()
        return fragmentView
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onSuccess(ticketReorganize: TicketReorganize) {
        fragmentView.progress_bar.visibility = View.GONE
        fragmentView.error_text.visibility = View.GONE
        fragmentView.all_information.visibility = View.VISIBLE

        fragmentView.reward.text = String.format("%.4f", ticketReorganize.totalReward)
        fragmentView.total_fee_spend.text = String.format("%.4f", ticketReorganize.totalFee)
        fragmentView.amount_live.text = String.format("%.4f", ticketReorganize.amountInLive)

        fragmentView.lived.text = ticketReorganize.totalTicketsLived.toString()
        fragmentView.total_ticket_voted.text = ticketReorganize.totalTicketsVoted.toString()
        fragmentView.avg_days.text = ticketReorganize.avgToVoted.toString()

    }

    override fun onError() {
        fragmentView.progress_bar.visibility = View.GONE
        fragmentView.error_text.visibility = View.VISIBLE
        fragmentView.all_information.visibility = View.GONE
    }

    override fun onReload() {
        fragmentView.progress_bar.visibility = View.VISIBLE
        fragmentView.error_text.visibility = View.GONE
        fragmentView.all_information.visibility = View.GONE
    }


}
