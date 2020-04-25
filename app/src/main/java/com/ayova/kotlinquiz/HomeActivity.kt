package com.ayova.kotlinquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayova.kotlinquiz.views.LoginFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().add(R.id.main_frame_container, LoginFragment()).commit()
    }
}
