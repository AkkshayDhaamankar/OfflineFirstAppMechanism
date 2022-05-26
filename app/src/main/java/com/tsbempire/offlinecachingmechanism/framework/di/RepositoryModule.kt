package com.tsbempire.offlinecachingmechanism.framework.di

import android.content.Context
import com.tsbempire.core.repository.CommentRepository
import com.tsbempire.offlinecachingmechanism.framework.RoomCommentDataSource
import com.tsbempire.offlinecachingmechanism.framework.workmanager.TaskScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideRepository(@ApplicationContext appContext: Context) = CommentRepository(
        RoomCommentDataSource(appContext),
        TaskScheduler(appContext)
    )
}