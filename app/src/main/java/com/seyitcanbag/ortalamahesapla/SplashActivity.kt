package com.seyitcanbag.ortalamahesapla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash_layout.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        val comingFromBelow = AnimationUtils.loadAnimation(this, R.anim.comingfrombelow)
        val fromAbove = AnimationUtils.loadAnimation(this, R.anim.fromabove)
        val goingUp = AnimationUtils.loadAnimation(this, R.anim.going_up)
        val goingDown = AnimationUtils.loadAnimation(this, R.anim.going_down)

        button.animation = comingFromBelow
        tvSplash.animation = fromAbove

        button.setOnClickListener(){
            button.startAnimation(goingDown)
            tvSplash.startAnimation(goingUp)

            object : CountDownTimer(1000,1000){
                override fun onTick(p0: Long) {

                }

                override fun onFinish() {
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }

            }.start()


        }

    }
}