package org.decred.ticket.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class DataModule {

    @Provides
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)


}