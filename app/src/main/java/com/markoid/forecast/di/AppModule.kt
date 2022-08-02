package com.markoid.forecast.di

import android.content.Context
import androidx.room.Room
import com.markoid.forecast.dao.WeatherDao
import com.markoid.forecast.database.WeatherDatabase
import com.markoid.forecast.datasource.WeatherDataSource
import com.markoid.forecast.datasource.WeatherDataSourceImpl
import com.markoid.forecast.network.WeatherService
import com.markoid.forecast.repository.WeatherRepository
import com.markoid.forecast.repository.WeatherRepositoryImpl
import com.markoid.forecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Provides
  @Singleton
  fun providesRetrofit(): Retrofit = Retrofit
    .Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  @Provides
  @Singleton
  fun providesWeatherService(
    retrofit: Retrofit
  ): WeatherService = retrofit.create(WeatherService::class.java)

  @Provides
  @Singleton
  fun providesWeatherRepository(
    impl: WeatherRepositoryImpl
  ): WeatherRepository = impl

  @Provides
  @Singleton
  fun providesWeatherDataSource(
    impl: WeatherDataSourceImpl
  ): WeatherDataSource = impl

  @Provides
  @Singleton
  fun provideAppDatabase(
    @ApplicationContext context: Context
  ): WeatherDatabase = Room
    .databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
    .fallbackToDestructiveMigration()
    .build()

  @Provides
  @Singleton
  fun provideWeatherDao(
    weatherDatabase: WeatherDatabase
  ): WeatherDao = weatherDatabase.weatherDao()
}