package org.decred.ticket.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {


    override fun onTokenRefresh() {
        //For registration of token
        val refreshedToken = FirebaseInstanceId.getInstance().token
        //To displaying token on logcat
        Log.d("TOKEN: ", refreshedToken)
    }

}