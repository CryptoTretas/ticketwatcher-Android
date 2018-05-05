package org.decred.ticket.future.association

interface AssociationContract {
    interface View {
        fun saveWalletIdSucess()
    }

    interface Presenter {
        fun onStart()
        fun saveWalletIdPreference(walletId: String)
    }
}