package com.github.aleksandrgrebenkin.kotlinapp.viewmodel

import com.github.aleksandrgrebenkin.kotlinapp.data.errors.NoAuthException
import com.github.aleksandrgrebenkin.kotlinapp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.kotlinapp.view.viewstate.SplashViewState

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let { SplashViewState(true) } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }

//    private var userLiveData: LiveData<User?>? = null
//    private val userObserver = object : Observer<User?> {
//        override fun onChanged(user: User?) {
//            viewStateLiveData.value = user?.let { SplashViewState(true) }
//                    ?: SplashViewState(error = NoAuthException())
//            userLiveData?.removeObserver(this)
//        }
//    }
//
//    fun requestUser() {
//        userLiveData = NotesRepository.getCurrentUser()
//        userLiveData?.observeForever(userObserver)
//    }
//
//    override fun onCleared() {
//        userLiveData?.removeObserver(userObserver)
//    }
}