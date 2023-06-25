package com.template

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowCompat
import com.template.UseCase.LogicBonusGame
import com.template.databinding.ActivityBonusGameBinding
@Suppress("DEPRECATION")

class BonusGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBonusGameBinding
    private lateinit var logicBonusGame: LogicBonusGame
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBonusGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        logicBonusGame = LogicBonusGame()
        val result = logicBonusGame.number()
        binding.apply {
            corob1.setOnClickListener {
               // Toast.makeText(this@BonusGameActivity, "Your reward: ${result.get("slot1")} coins",Toast.LENGTH_LONG).show()
                val builder = AlertDialog.Builder(this@BonusGameActivity)
                builder.setMessage("Your reward: ${result.get("slot1")} coins")
                    .setPositiveButton("claim"){dialog, id->
                        dialog.cancel()
                    }
                builder.show()
                result
            }
            corob2.setOnClickListener {
                //Toast.makeText(this@BonusGameActivity, "Your reward: ${result.get("slot2")} coins",Toast.LENGTH_LONG).show()
                val builder = AlertDialog.Builder(this@BonusGameActivity)
                builder.setMessage("Your reward: ${result.get("slot2")} coins")
                    .setPositiveButton("claim"){dialog, id->
                        dialog.cancel()
                    }
                builder.show()
                result
            }
            corob3.setOnClickListener {
                //Toast.makeText(this@BonusGameActivity, "Your reward: ${result.get("slot3")} coins",Toast.LENGTH_LONG).show()
                val builder = AlertDialog.Builder(this@BonusGameActivity)
                builder.setMessage("Your reward: ${result.get("slot3")} coins")
                    .setPositiveButton("claim"){dialog, id->
                        dialog.cancel()
                    }
                builder.show()
                result
            }
        }
    }
}