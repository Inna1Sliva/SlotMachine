package com.template

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.WindowCompat
import com.template.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
  private var progressStatus = 0
    private var handler = Handler()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        binding.bottPlay.visibility = View.VISIBLE
        binding.statusProgress.visibility  =View.GONE
        binding.progressBar.visibility = View.GONE
        binding.progresStyle.visibility = View.GONE


      binding.bottPlay.setOnClickListener {
          binding.progresStyle.visibility = View.VISIBLE
          binding.statusProgress.visibility  =View.VISIBLE
          binding.bottPlay.visibility = View.GONE
          binding.progressBar.visibility = View.VISIBLE
          binding.progressBar.progress +=100
          Thread(Runnable {
              while (progressStatus<100){
                progressStatus += 10
              Thread.sleep(100)
              handler.post {
                  binding.statusProgress.text = "${progressStatus}%"

               }
                }

              }).start()
          handler.postDelayed({
              if (progressStatus==100){
                  startActivity(Intent (this, GameActivity::class.java))
                  finish()
              }
              else{
                  Toast.makeText(this, "Loading",Toast.LENGTH_LONG).show()
              }
          },2000)



     }



    }
}

