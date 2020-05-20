package xyz.wayhua.kivy101.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.wayhua.kivy101.data.repository.AppRepository
import xyz.wayhua.kivy101.data.repository.impl.AppRepositoryImpl
import xyz.wayhua.kivy101.data.repository.room.AppDatabase
import xyz.wayhua.kivy101.rx.ApplicationSchedulerProvider
import xyz.wayhua.kivy101.rx.SchedulerProvider
import xyz.wayhua.kivy101.ui.main.fragment.PostViewModel

val rxModule = module {
    // Rx Schedulers
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}
val viewModelModule = module {
    viewModel { PostViewModel(get(), get()) }
}
val roomModule = module {
    // Room Database
    single {

        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "db_app")

            .fallbackToDestructiveMigration()
            .build()
    }

    // Expose Dao directly

    single { get<AppDatabase>().postDao() }
}
val repoModule = module {
    // App Data Repository
    single { AppRepositoryImpl(get(), get()) as AppRepository }
}

val allModules = listOf(rxModule, roomModule, viewModelModule,remoteDatasourceModule, repoModule)