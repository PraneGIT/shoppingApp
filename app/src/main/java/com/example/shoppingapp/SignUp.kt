package com.example.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.shoppingapp.databinding.ActivitySignupBinding
import com.example.shoppingapp.models.user
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUp : BaseActivity() {

    private var binding: ActivitySignupBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        FirebaseApp.initializeApp(this);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding?.btnSignup?.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        val name:String=binding!!.etName!!.text!!.toString().trim{it <= ' '}
        val email:String=binding!!.etEmail!!.text!!.toString().trim{it <= ' '}
        val password:String=binding!!.etPassowrd!!.text!!.toString().trim{it <= ' '}

        if(validateForm(name,email,password)){
            showProgressDialog("please wait...")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task!!.result!!.user!!
                    val registeredEmail = firebaseUser!!.email!!

                    val user= user(firebaseUser.uid,name,registeredEmail)
                    FirestoreClass().registerUser(this,user)


                } else {
                    Toast.makeText(this, task!!.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateForm(name:String,email:String,password:String):Boolean{
        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("please enter name")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("please enter email")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("please enter password")
                false
            }else ->{
                true;
            }
        }
    }

    fun userRegisteredSuccess(){
        Toast.makeText(
            this,
            "you have successfully registered ",
            Toast.LENGTH_LONG
        ).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}