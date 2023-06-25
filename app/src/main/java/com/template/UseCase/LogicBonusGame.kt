package com.template.UseCase

import kotlin.random.Random

class LogicBonusGame {

    private var number = mutableListOf<Int>(10,100,300,500,1000)

    fun randomNumberBonus():Int{
        val random= Random
        return number[random.nextInt(number.size-1)]
    }
    fun number():HashMap<String, Int> {
        var hashmap: HashMap<String, Int> = HashMap()
        hashmap.put("slot1", randomNumberBonus())
        hashmap.put("slot2", randomNumberBonus())
        hashmap.put("slot3", randomNumberBonus())
        return hashmap
    }
}