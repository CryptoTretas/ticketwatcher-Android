package org.decred.ticket.future.home.fragment.balance

import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_extract.view.*
import org.decred.ticket.R
import org.decred.ticket.data.Ticket
import org.decred.ticket.future.home.fragment.extract.ExtractContract
import org.decred.ticket.future.home.fragment.extract.ExtractRecycleView
import javax.inject.Inject

class ExtractFragment : DaggerFragment(), ExtractContract.View {
    private lateinit var fragmentView: View


    @Inject
    lateinit var presenter: ExtractContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.fragment_extract, container, false)
        fragmentView.extract_list.layoutManager = LinearLayoutManager(activity)
        presenter.onStart()
        return fragmentView
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onSuccess(ticketsList: List<Ticket>) {
        fragmentView.progress_bar.visibility = View.GONE
        fragmentView.error_text.visibility = View.GONE
        fragmentView.extract_list.visibility = View.VISIBLE

        fragmentView.extract_list.adapter = ExtractRecycleView(activity, ticketsList)
        fragmentView.extract_list.adapter.notifyDataSetChanged()
    }

    override fun onReload() {
        fragmentView.progress_bar.visibility = View.VISIBLE
        fragmentView.error_text.visibility = View.GONE
        fragmentView.extract_list.visibility = View.GONE
    }

    override fun onError() {
        fragmentView.progress_bar.visibility = View.GONE
        fragmentView.error_text.visibility = View.VISIBLE
        fragmentView.extract_list.visibility = View.GONE
    }


}
