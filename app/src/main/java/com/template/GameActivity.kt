package com.template

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.template.UseCase.LogiсSlotMachine
import com.template.databinding.ActivityGameBinding
import com.template.fragment.BonusFragment
import com.template.fragment.ReplayFragment

@Suppress("DEPRECATION")
class GameActivity : AppCompatActivity(),Animation.AnimationListener {
    private lateinit var imageAnimation: Animation
    private lateinit var imageOutAnimation: Animation
    private lateinit var lineAnimation: Animation
    private lateinit var binding:ActivityGameBinding
    private lateinit var  logiсSlotMachine: LogiсSlotMachine

    private var startPrise :Int = 100
    private var bet:Int = 10
    private var vinerSum:Int= 0
    private var bonus:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        imageAnimation = AnimationUtils.loadAnimation(this, R.anim.image_animation)
        lineAnimation = AnimationUtils.loadAnimation(this, R.anim.image_animation2)

        imageOutAnimation = AnimationUtils.loadAnimation(this, R.anim.image_animation2)

        // imageOutAnimation.setAnimationListener(this)
        imageAnimation.setAnimationListener(this)

        logiсSlotMachine = LogiсSlotMachine()
         binding.betNumber.text = bet.toString()
            binding.textMoney.text = logiсSlotMachine.money(startPrise)
                binding.line.visibility=View.GONE


        val imageRandom = logiсSlotMachine.imageChange()
        binding.ImageIcon2.setImageResource(imageRandom.get("slot1")!!)
        binding.ImageIcon1.setImageResource(imageRandom.get("slot2")!!)
        binding.ImageIcon3.setImageResource(imageRandom.get("slot3")!!)

        binding.min.setOnClickListener {
            val betSum = binding.betNumber.text.toString().toInt()
            binding.betNumber.text = logiсSlotMachine.betMin(betSum).toString()
        }

        binding.plus.setOnClickListener {
            val betSum = binding.betNumber.text.toString().toInt()
            val money = binding.textMoney.text.toString().toInt()
            //val sum =bet +betSum
            binding.betNumber.text = logiсSlotMachine.betPlus(money, betSum).toString()

        }
        binding.bottBack.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to get out?")
                .setPositiveButton("Exit"){dialog, id->
                    finish()
                }
            builder.show()
        }
        binding.bottBonus.setOnClickListener {
            startActivity(Intent(this, BonusGameActivity::class.java))
        }


        binding.buttSplit.setOnClickListener {
         binding.line.visibility = View.GONE
        var image1 = binding.viewFrame1
        var image2 = binding.viewFrame2
        var image3 = binding.viewFrame3
        var image4 = binding.viewFrame4

        var betNumber = binding.betNumber.text.toString().toInt()
        val bonusResult = logiсSlotMachine.randomNumber()
        bonus++
        if (bonus == bonusResult) {
            imageAnimation = AnimationUtils.loadAnimation(this, R.anim.image_animation)
            image1.startAnimation(imageAnimation)
            image2.startAnimation(imageAnimation)
            image3.startAnimation(imageAnimation)
            image4.startAnimation(imageAnimation)
            startPrise -= betNumber
            binding.textMoney.text=logiсSlotMachine.money(startPrise)

            binding.ImageIcon2.setImageResource(R.drawable.ico_8)
            binding.ImageIcon5.setImageResource(R.drawable.ico_8)
            binding.ImageIcon8.setImageResource(R.drawable.ico_8)
            binding.ImageIcon8.setImageResource(R.drawable.ico_8)
            stopAnimation()


        } else {
             binding.line.visibility=View.GONE
            startPrise -= betNumber
            binding.textMoney.text=logiсSlotMachine.money(startPrise)

            Toast.makeText(this,"${bonus}", Toast.LENGTH_LONG).show()

            var imageRandom = logiсSlotMachine.imageChange()
             var result = logiсSlotMachine.analyseResult(imageRandom)
                startPrise  += result
                 analyseResultSum(result)
              binding.ImageIcon2.setImageResource(imageRandom.get("slot1")!!)
             binding.ImageIcon5.setImageResource(imageRandom.get("slot2")!!)
             binding.ImageIcon8.setImageResource(imageRandom.get("slot3")!!)
             binding.ImageIcon8.setImageResource(imageRandom.get("slot4")!!)

           image1.startAnimation(imageAnimation)
            image2.startAnimation(imageAnimation)
             image3.startAnimation(imageAnimation)
            image4.startAnimation(imageAnimation)
             analysisNullResult(startPrise)
             }

      }

      }

        private fun stopAnimation() {
            var image1 = binding.viewFrame1
            var image2 = binding.viewFrame2
            var image3 = binding.viewFrame3
            var image4 = binding.viewFrame4
            Handler(Looper.getMainLooper()).postDelayed({
                image1.startAnimation(imageOutAnimation)
            }, 1000)
            Handler(Looper.getMainLooper()).postDelayed({
                image2.startAnimation(imageOutAnimation)
            }, 2500)
            Handler(Looper.getMainLooper()).postDelayed({
                image3.startAnimation(imageOutAnimation)
            }, 4500)
            Handler(Looper.getMainLooper()).postDelayed({
                image4.startAnimation(imageOutAnimation)
                 binding.line.visibility = View.VISIBLE
                binding.line.startAnimation(lineAnimation)
                startPrise += 2000
                binding.textMoney.text =logiсSlotMachine.money(startPrise)
                bonus = 0
            }, 6500)
            Handler(Looper.getMainLooper()).postDelayed({
                val bonusFragment = BonusFragment()
                replaceFragment(bonusFragment)
            }, 8000)

        }

        private fun analysisNullResult(sum: Int) {
            if (sum == 0) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val fragment = ReplayFragment()
                    replaceFragment(fragment)
                    startPrise = 100
                    bet = 10
                    binding.textMoney.text =logiсSlotMachine.money(startPrise)
                    binding.betNumber.text = bet.toString()
                }, 8000)


            }

        }

        private fun analyseResultSum(sum: Int) {
            var image1 = binding.viewFrame1
            var image2 = binding.viewFrame2
            var image3 = binding.viewFrame3
            var image4 = binding.viewFrame4
            Handler(Looper.getMainLooper()).postDelayed({
                image1.startAnimation(imageOutAnimation)
            }, 1000)
            Handler(Looper.getMainLooper()).postDelayed({
                image2.startAnimation(imageOutAnimation)
            }, 2500)
            Handler(Looper.getMainLooper()).postDelayed({
                image3.startAnimation(imageOutAnimation)
            }, 4500)
            Handler(Looper.getMainLooper()).postDelayed({
                image4.startAnimation(imageOutAnimation)
                if (sum > 0) {
                    binding.line.visibility = View.VISIBLE
                    binding.line.startAnimation(lineAnimation)
                    binding.textMoney.text =logiсSlotMachine.money(startPrise)
                } else {
                    binding.line.visibility = View.GONE
                }
            }, 6500)


        }

        private fun replaceFragment(fragment: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTrasaction = fragmentManager.beginTransaction()
            fragmentTrasaction.replace(R.id.fragment, fragment)
            fragmentTrasaction.commit()

        }

        override fun onAnimationStart(p0: Animation?) {
            // var image1 =  binding.viewFrame1
            //  image1.startAnimation(imageAnimation)


        }

        override fun onAnimationEnd(p0: Animation?) {
            Toast.makeText(this, "endAnimation", Toast.LENGTH_LONG).show()
            var image1 = binding.viewFrame1
            var image2 = binding.viewFrame2
            var image3 = binding.viewFrame3
            var image4 = binding.viewFrame4
            Handler(Looper.getMainLooper()).postDelayed({
                image1.startAnimation(imageOutAnimation)
            }, 1000)
            Handler(Looper.getMainLooper()).postDelayed({
                image2.startAnimation(imageOutAnimation)
            }, 2500)
            Handler(Looper.getMainLooper()).postDelayed({
                image3.startAnimation(imageOutAnimation)
            }, 4500)
            Handler(Looper.getMainLooper()).postDelayed({
                image4.startAnimation(imageOutAnimation)
            }, 6500)


        }

        override fun onAnimationRepeat(p0: Animation?) {
            TODO("Not yet implemented")
        }
    }


