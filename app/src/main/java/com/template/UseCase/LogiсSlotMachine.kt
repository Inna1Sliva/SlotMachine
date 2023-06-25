package com.template.UseCase

import com.template.R
import kotlin.random.Random

class LogiсSlotMachine {

    private var imageSlot= mutableListOf<Int>(
        R.drawable.ico_1,
        R.drawable.ico_2, R.drawable.ico_3, R.drawable.ico_4,
        R.drawable.ico_5, R.drawable.ico_6, R.drawable.ico_7,
        R.drawable.ico_8
    )
    private var number = mutableListOf<Int>(1,2,3,4,5,6,7,8,9)

    fun randomNumber():Int{
        val random=Random
        return number[random.nextInt(number.size-1)]
    }

    fun randomImageSlot():Int{
     val random:Random = Random
        return imageSlot[random.nextInt(imageSlot.size-1)]
    }
fun imageChange():HashMap<String, Int>{
    var hashmap:HashMap<String, Int> = HashMap()
    hashmap.put( "slot1",randomImageSlot())
    hashmap.put( "slot2",randomImageSlot())
    hashmap.put( "slot3",randomImageSlot())
    hashmap.put( "slot4",randomImageSlot())
    return hashmap
}
    fun analyseResult(result: HashMap<String,Int>):Int{
        if (result.get("slot1")==result.get("slot2")&&result.get("slot2")==result.get("slot3") &&result.get("slot3")==result.get("slot4") ){
            return 1000
        }
        if (result.get("slot1") == result.get("slot2")||result.get("slot2") == result.get("slot3")){
            return  100
        }
        return 0
    }
    fun betPlus(prise: Int, bet: Int):Int{
        var bid = bet
        if (prise!= 0 && bet < prise){
            bid+=10
            return bid
        }
        if(bet>prise){
            return bet
        }
        return bet
    }
    fun betMin(bet: Int):Int{
        var bid = bet
        if (bet>10){
           bid-=10
            return bid
        }
        if (bet == 10){
            return bet
        }
        return bet
    }
   fun money(sum:Int):String{
       val price = "00000000"  + sum
       val sub = price.substring(price.length-8)

       return sub
   }


}