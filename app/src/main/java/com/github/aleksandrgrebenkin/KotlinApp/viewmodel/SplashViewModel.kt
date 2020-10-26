package com.github.aleksandrgrebenkin.KotlinApp.viewmodel

import com.github.aleksandrgrebenkin.KotlinApp.data.errors.NoAuthException
import com.github.aleksandrgrebenkin.KotlinApp.model.data.NotesRepository
import com.github.aleksandrgrebenkin.KotlinApp.view.viewstate.SplashViewState

class SplashViewModel : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever {
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