package com.tsbempire.offlinecachingmechanism.framework.di

import com.tsbempire.network.FirestoreHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FirestoreHelperModule {
    @Provides
    fun provideFirestoreHelper() = FirestoreHelper()
}