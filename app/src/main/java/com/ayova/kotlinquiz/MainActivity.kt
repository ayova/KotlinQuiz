package com.ayova.kotlinquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import render.animations.Attention
import render.animations.Fade
import render.animations.Render

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logo = findViewById<ImageView>(R.id.logo_imageview)
        val render = Render(this)

        render.setAnimation(Attention().Tada(logo).setDuration(2000))
        render.start()
        Handler().postDelayed({
            render.setAnimation(Fade().Out(logo))
            render.start()
        }, 2000)
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()
        }, 3000)


    }
}
