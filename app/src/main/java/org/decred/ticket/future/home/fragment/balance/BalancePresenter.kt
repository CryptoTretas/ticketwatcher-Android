package org.decred.ticket.future.home.fragment.balance

import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.home.HomeContract
import javax.inject.Inject

class BalancePresenter @Inject constructor(
        private val view: BalanceContract.View,
        private val userPreference: UserPreference) : BalanceContract.Presenter