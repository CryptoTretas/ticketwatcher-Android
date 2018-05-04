package org.decred.ticket.future.association

interface AssociationContract {
    interface View {
        fun isLogged()
        fun newLogin()
    }

    interface Presenter {
        fun onStart()
    }
}