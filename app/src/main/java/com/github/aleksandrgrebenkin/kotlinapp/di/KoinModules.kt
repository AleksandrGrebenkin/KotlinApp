package com.github.aleksandrgrebenkin.kotlinapp.di

import com.github.aleksandrgrebenkin.kotlinapp.data.provider.DataProvider
import com.github.aleksandrgrebenkin.kotlinapp.data.provider.FirestoreDataProvider
import com.github.aleksandrgrebenkin.kotlinapp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.MainViewModel
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.NoteViewModel
import com.github.aleksandrgrebenkin.kotlinapp.viewmodel.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirestoreDataProvider(get(), get()) } bind DataProvider::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}