package com.example.shoppingapp

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.util.Log.e
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoppingapp.models.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.type.Money
import java.sql.Array

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()

    //Authentication Logic
    fun registerUser(activity: SignUp, userinfo: user) {
        mFireStore.collection(Constants.USERS).document(getCurrentUserId()).set(
            userinfo,
            SetOptions.merge()
        ).addOnSuccessListener {
            activity.userRegisteredSuccess()
        }.addOnFailureListener { e ->
            e(activity.javaClass.simpleName, e.toString())
        }
    }

    fun getCurrentUserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

}

