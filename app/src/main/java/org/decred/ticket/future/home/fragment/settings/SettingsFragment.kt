package org.decred.ticket.future.home.fragment.settings

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_settings.view.*
import org.decred.ticket.BuildConfig
import org.decred.ticket.R
import org.decred.ticket.future.association.AssociationActivity
import javax.inject.Inject


class SettingsFragment : DaggerFragment(), SettingsContract.View {

    @Inject
    lateinit var presenter: SettingsContract.Presenter

    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.fragment_settings, container, false)
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(getString(R.string.wallet), BarcodeFormat.QR_CODE, 400, 400)
        fragmentView.qr_code_donation.setImageBitmap(bitmap)
        listeners()
        fragmentView.version.text = BuildConfig.VERSION_NAME
        return fragmentView
    }


    private fun listeners() {
        fragmentView.button.setOnClickListener {
            presenter.onDestrory()
            val intent = Intent(context, AssociationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            activity!!.finish()
        }

        fragmentView.wallet_id.setOnClickListener {
            clipboardSuccess()
        }
    }

    private fun clipboardSuccess() {
        val clipboard = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Donate Wallet", getString(R.string.wallet))
        clipboard.primaryClip = clip
        Toast.makeText(activity, "Donate address now in your clipboard", Toast.LENGTH_LONG).show()
    }

}
