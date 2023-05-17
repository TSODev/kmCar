package fr.tsodev.kmcar.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.tsodev.kmcar.repository.FirestoreRepository
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
//    @Named("kmEntries")
        fun provideKmEntryRepository() = FirestoreRepository(
            queryEntry = FirebaseFirestore.getInstance()
                .collection("kmEntries"),
//                .orderBy("date", Query.Direction.ASCENDING)
            queryCar = FirebaseFirestore.getInstance()
            .collection("cars")
    )

//    @Provides
//    @Singleton
//    @Named("cars")
//    fun provideCarsRepository() = FirestoreRepository(
//        queryCar = FirebaseFirestore.getInstance()
//            .collection("cars")
//    )

}