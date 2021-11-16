package com.project.simplecoffee.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepo {
    private var firebaseUser: FirebaseUser? = null

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        if (auth.currentUser != null) {
            firebaseUser = auth.currentUser
        }
    }

    fun isLoggedIn(): Boolean {
        return firebaseUser != null
    }

    fun getUser(): FirebaseUser? {
        return firebaseUser
    }

    suspend fun signIn(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).await()
        firebaseUser = auth.currentUser
    }

    suspend fun signUp(email: String, pwd: String) {
        auth.createUserWithEmailAndPassword(email, pwd).await()
        firebaseUser = auth.currentUser
    }

    fun signOut() {
        auth.signOut()
        firebaseUser = null
    }

    fun getUID(): String? {
        return firebaseUser?.uid
    }
}