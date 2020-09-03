package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        button.setOnClickListener(){
            onAuth()
        }

        reg_up.setOnClickListener(){
            onReg()
        }

    }


    fun onAuth(){
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(emailID.text.toString(),passwordID.text.toString())
            .addOnCompleteListener(){
            if(it.isSuccessful){
                startSecondActive()
            } else {
                when(it.exception){
                    is FirebaseAuthInvalidCredentialsException ->{
                        Toast.makeText(this,"Неверный логин или пароль",Toast.LENGTH_SHORT).show()
                    }
                    is FirebaseNetworkException -> {
                        Toast.makeText(this,"Проверьте подключение к сети",Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Ты лох, братишка", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun onReg(){
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(emailID.text.toString(),passwordID.text.toString())
            .addOnCompleteListener(){
                if(it.isSuccessful){
                    startSecondActive()
                } else {
                    when(it.exception){
                        is FirebaseAuthInvalidCredentialsException ->{
                            Toast.makeText(this,"Такой аккаунт уже существует",Toast.LENGTH_SHORT).show()
                        }
                        is FirebaseNetworkException -> {
                            Toast.makeText(this,"Проверьте подключение к сети",Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this, "Ты лох, братишка", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    fun startSecondActive(){
        val nextIntenet = Intent(this,SecondActivity::class.java)
        startActivity(nextIntenet)
    }
}