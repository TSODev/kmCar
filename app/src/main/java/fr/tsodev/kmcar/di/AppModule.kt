package fr.tsodev.kmcar.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.tsodev.kmcar.data.KmRecordDB
import fr.tsodev.kmcar.data.kmRecordDBDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRecordsDao(kmRecordDB: KmRecordDB) : kmRecordDBDao
    = kmRecordDB.kmRecordDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): KmRecordDB
    = Room.databaseBuilder(
        context,
        KmRecordDB::class.java,
        "kmRecord_db"
    ).fallbackToDestructiveMigration()
        .build()
}