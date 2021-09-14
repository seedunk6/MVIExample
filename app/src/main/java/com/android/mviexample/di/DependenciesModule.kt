package com.android.mviexample.di

import com.android.mviexample.data.TaskMapper
import com.android.mviexample.data.network.TaskApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenciesModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskApiClient(retrofit: Retrofit): TaskApiClient {
        return retrofit.create(TaskApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideMapper(): TaskMapper {
        return TaskMapper()
    }
}