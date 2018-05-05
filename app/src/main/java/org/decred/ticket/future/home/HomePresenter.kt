package org.decred.ticket.future.home

import org.decred.ticket.DAO.UserPreference
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private val view: HomeContract.View,
        private val userPreference: UserPreference) : HomeContract.Presenter