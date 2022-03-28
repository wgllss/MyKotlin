package com.example.myapplication.dest

class Test private constructor() {
    companion object {
        private var mInstance: Test? = null
            get() {
                if (field == null) field = Test()
                return field
            }

        @Synchronized
        fun getMInsstace(): Test {
            return mInstance!!
        }
    }

    fun print(){
        println("wg wg ")
    }
}