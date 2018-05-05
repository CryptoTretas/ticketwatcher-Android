package org.decred.ticket.future.association

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_association.*
import org.decred.ticket.R
import org.decred.ticket.future.home.HomeActivity
import javax.inject.Inject


class AssociationActivity : DaggerAppCompatActivity(), AssociationContract.View {

    @Inject
    lateinit var presenter: AssociationContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_association)
        listeners()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }


    private fun listeners() {
        scan.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setCameraId(0)  // Use a specific camera of the device
            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }
    }

    private fun showAlertDialog(walletId: String) {
        AlertDialog.Builder(this)
                .setTitle("It`s your Wallet ID?")
                .setMessage(walletId)
                .setCancelable(false)
                .setNegativeButton("RESCAN", { dialog, _ -> dialog.dismiss() })
                .setPositiveButton("YEP", { _, _ -> presenter.saveWalletIdPreference(walletId)})
                .create()
                .show()
    }

    override fun saveWalletIdSucess(){
        val intent = HomeActivity.getCallingIntent(this)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                showAlertDialog(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, AssociationActivity::class.java)
    }

}