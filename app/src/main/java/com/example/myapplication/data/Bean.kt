package com.example.myapplication.data

import android.util.Log

data  class Bean(var name0:String) {
    var name :String=""
        get()="小乖乖"
        set
    var arg:Int = 0
        get()=field
        set(value) {
            field = 39
        }

    init {
        this.name=name0;
        Log.e("Bean", name);
    }

    constructor( name:String,arg:Int):this(name){
        this.arg=arg
    }

    lateinit var mc:String ;

    fun nmd(){
        Log.e("@@@","dfada");
    }



}
