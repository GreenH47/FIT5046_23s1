package com.example.playnumbers

class PlayNumber (private var number: Int) {
    init{
        if(number <10) number=10
    }
    //Pre-Increment Operator '++a' increments the value of a variable abruptly
    fun increment(num: Int): Int {
        //fun parameters are read-only values and cannot be reassigned so we consider a local var
        var numVar =num
        return ++numVar;
    }
    //this method will get the max number when the class is instantiated
    fun random(): Int {
        return (1..number).random()
    }
}