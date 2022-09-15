package com.example.myapplication.ui.main.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.activity.BaseActivity
import com.example.myapplication.data.Bean
import com.example.myapplication.ui.main.fragment.MainFragment
import com.example.myapplication.data.Response
import com.example.myapplication.dest.Test
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : BaseActivity(), View.OnClickListener {
    val TAG = "MainActivity"
    val s: String = "我是String类型的";
    val listA: List<Class<out Activity>> = listOf(TestActivity::class.java)
    val listB: List<Class<in Activity>> = listOf(Context::class.java)


    companion object {
        val SEARCH_CONTENT_KEY = "SEARCH_CONTENT_KEY"
        fun startTestActivity(context: Context, search: String?) {
            var intent = Intent(context, TestActivity::class.java);
            intent.putExtra(SEARCH_CONTENT_KEY, search)
            context.startActivity(intent)
        }
    }

    override fun initControl(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            var srt = intent.getStringExtra(SEARCH_CONTENT_KEY)
            setFramgment(MainFragment.newInstance(srt!!), R.id.container)
        }
        Setup()
//        var layout: View = findViewById(R.id.container);
//        layout.setBackgroundColor(Color.parseColor("#0effff"));
//            container.setOnClickListener {
//                var dd = "12121212";
//                val cc = "2323"
//                dd = "cvcccd";
//                println("sss${dd}你妹的${cc}");
////                it.setBackgroundColor(Color.parseColor("#0ef000"));
//                var i = 3;
//                var m = 5;
//                var c = i + m;
//                var k = 3.6f;
//                var f = 2.1f;
//                var n = k - f;
//                var w = m / i;
//
//                var kk = su(2, 6);
//                Log.e(TAG, "c=${c} n=${n} w=${w} kk={$kk}");
//                emdafa(1, 3, 5, 7);
//                val nudll = cheeckNUll("b");
//                if (null == nudll) {
//                    MyLog(3333)
//                } else {
//
//                }
//
//                for (i in 5..9) MyLog(i)
//                for (i in 9..6) MyLog(i) // 什么都不输出
//                if (i in 1..10) { // 等同于 1 <= i && i <= 10
//                    MyLog(i)
//                }
//
//                // 使用 step 指定步长
//                for (i in 1..4 step 2) MyLog(i) // 输出“13”
//                for (i in 4 downTo 1 step 2) MyLog(i) // 输出“42”
//                // 使用 until 函数排除结束元素
//                for (i in 15 until 19) {   // i in [1, 10) 排除了 10
//                    MyLog(i)
//                }
//                var ms: Int = 1000;
//                MyLog(ms.toString())
//
//                var aar = arrayOf("a", "b", "c")
//                for (c in aar) {
//                    MyLog(c)
//                }
//                for (id in aar.indices) {
//                    MyLog(aar[id])
//                }
//                MyLog("##############")
//                //[0,2,4]
////                val b = Array(3, { i -> (i * 2) })
//                val b = Array(5, { i -> (i * 1) })
//
//
//                for (i in b.indices) {
//                    MyLog(b[i])
//                }
//
//                val x: IntArray = intArrayOf(1, 2, 3)
//                MyLog("11111111111")
//                MyLog(x[0])
//                x[0] = x[1] + x[2]
//                MyLog(x[0])
//
//                val text = """
//    多行字符串
//    多行字符串
//    """
//                MyLog(text)   // 输出有一些前置空格
//
//                val text1 = """
//    |多行字符串
//    |菜鸟教程
//    |多行字符串
//    |Runoob
//    """.trimMargin()
//
//                MyLog(text1)   // 输出有一些前置空格
//
//                test();
//
//
//            }
    }

    override fun bindEvent() {
    }

    override fun initValue() {
    }

    override fun onClick(p0: View?) {
    }


    fun sun(i: Int, k: Int): Int {
        return i + k;
    }

    fun sum(i: Int, m: Int) = i + m;

    public fun su(i: Int, l: Int): Int = i + l;

    fun emdafa(vararg c: Int) {
        for (vt in c) {
            MyLog(vt);
        }
    }


    fun MyLog(k: String) {
        Log.e(TAG, "打印结果：${k}");
    }

    fun MyLog(k: Int) {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        val subLumbda: (String, String) -> String = { m, n -> "${m}和${n}" }
        MyLog(subLumbda("涨", "的"));
        Log.e(TAG, "打印结果：${k}");
    }

    fun cheeckNUll(arg: String): Int? {
        return arg.toIntOrNull();
    }

    fun test() {
        var x = 0
        if (x > 0) {
            MyLog("x 大于 0")
        } else if (x == 0) {
            MyLog("x 等于 0")
        } else {
            MyLog("x 小于 0")
        }

        var a = 1
        var b = 2
        val c = if (a >= b) a else b
        MyLog("c 的值为 $c")

        val k = 5
        var y = 9
        if (k in 1..5) {
            MyLog("k 在区间内")
        }
        x = 2
        when (x) {
            1 -> {

            }
            2 -> {
                MyLog("x = 2")
            }
            in 1..10 -> MyLog("ddd")
            !in 2..25 -> MyLog("dfdd")
            is Int -> MyLog("")
        }

        var d: Double = 3e999;
        MyLog(d.toString())
        val array: IntArray = intArrayOf(1, 2, 3)
        for ((index, value) in array.withIndex()) {
            MyLog("the element at $index is $value")
        }

//        var x = 5
        while (x > 0) {
            MyLog(x--)
        }
        MyLog("----do...while 使用-----")
//        var y = 5
        do {
            MyLog(y--)
        } while (y > 0)


        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (j == 2) break@loop
            }
            MyLog("i=$i");
        }
        MyLog("loop");

        var bean = Bean("张三", 8);
//        bean.name="";
        MyLog(bean.name)
        MyLog(bean.arg)

//        var mycala :MyClass()


        MyClass().setInteraf(object : InterfaceI {
            override fun test(msg: String) {
                MyLog(msg)
            }
        })
        bean.nmd()
        bean.getdf()
        bean.dsfds = "???";
        MyLog(bean.dsfds)

        var res = Response(0, "22")

        var res2 = res.copy(errorMessage = "3g");
        MyLog("code:${res2.code}---message:${res2.errorMessage}")

        var (name0) = bean
        MyLog(name0)
    }

    var Bean.dsfds: String
        get() = this.name
        set(value) {

        }


    fun Bean.getdf() {
        MyLog("扩展函数")
    }

    class MyClass {
        fun setInteraf(inter: InterfaceI) {
            var msg: String = "小皮蛋";
            inter.test(msg);
        }
    }

    interface InterfaceI {
        fun test(msg: String);
    }

    fun Setup() {
        Test.getMInsstace().print()
    }


}