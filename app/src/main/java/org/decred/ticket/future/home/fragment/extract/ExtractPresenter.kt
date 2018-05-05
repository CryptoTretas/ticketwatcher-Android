package org.decred.ticket.future.home.fragment.extract

import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.home.HomeContract
import javax.inject.Inject

class ExtractPresenter @Inject constructor(
        private val view: ExtractContract.View,
        private val userPreference: UserPreference) : ExtractContract.Presenter