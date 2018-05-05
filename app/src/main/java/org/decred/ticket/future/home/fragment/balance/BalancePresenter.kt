package org.decred.ticket.future.home.fragment.balance

import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.home.HomeContract
import javax.inject.Inject

class BalancePresenter @Inject constructor(
        private val view: HomeContract.View,
        private val userPreference: UserPreference) : HomeContract.Presenter