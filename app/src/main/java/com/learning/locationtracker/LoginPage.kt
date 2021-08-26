package com.learning.locationtracker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//import com.sawolabs.androidsdk.Sawo

class LoginPage : AppCompatActivity() {
    private lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        initViews()
        loginBtn.setOnClickListener{
//            Sawo(
//                this,
//                "636ff13c-7261-4c62-966a-036ca44560b7", // your api key
//                "61274de7be7d45af4fbc6fe6QuBABIj2pDthYEUAkhhstEPX"  // your api key secret
//            ).login(
//                "email", // can be one of 'email' or 'phone_number_sms'
//                MainActivity::class.java.name // Callback class name
//            )
        }
    }

    private fun initViews(){
        loginBtn = findViewById(R.id.loginBtn)
    }
}