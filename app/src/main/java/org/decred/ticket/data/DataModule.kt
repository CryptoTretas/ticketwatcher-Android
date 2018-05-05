package org.decred.ticket.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Module
class DataModule {

    @Provides
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)


}