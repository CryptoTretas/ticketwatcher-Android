package org.decred.ticket.future.splash


interface SplashContract{
    interface View {
        fun isLogged()
        fun newLogin()
    }

    interface Presenter {
        fun onStart()
    }
}