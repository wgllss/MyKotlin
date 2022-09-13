package com.example.myapplication.ui.main.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.base.activity.BaseActivity
import com.example.myapplication.data.moshi.Person
import com.example.myapplication.ex.logE
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.launch

class TestMoshiActivity : BaseActivity(), View.OnClickListener {

    override fun initControl(savedInstanceState: Bundle?) {
        setContentView(R.layout.test_activity)
    }

    override fun bindEvent() {
    }

    override fun initValue() {
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.mater_btn -> {
                lifecycleScope.launch {
                    val person = Person("xiaoming", 12)
                    //创建Moshi，通过导入implementation "com.squareup.moshi:moshi-kotlin:1.11.0" 包的话必须设置KotlinJsonAdapterFactory()
                    val moshi = Moshi.Builder().build()


                    //获取对应类的adapter
                    val adapter = moshi.adapter(Person::class.java)
//                    val json: String = adapter.toJson(person)
                    val json: String = moshiToJson(person)
                    logE("json222:--> ${json}")

                    val str = "{\"name\":\"xiaoming889\",\"age\":1232,\"country\":\"中国32\"}"
//                    val str = "{\"age\":12,\"country\":\"中国\"}"
//                    val person1 = adapter.fromJson(str)
                    val person1 = moshiToAny(Person::class.java, str)
                    logE("333 person1.name:${person1?.name} person1.age:${person1?.age} person1.country:${person1?.country}")


                    //解析list
                    val json2 = "[{\"name\":\"xiaoAA@@@Aming\",\"age\":12,\"country\":\"中国\"},{\"name\":\"xiaoli\",\"age\":22,\"country\":\"美国\"}]"
                    val listOfPersonsType = Types.newParameterizedType(List::class.java, Person::class.java)
//                    val build = Moshi.Builder().build()
//
//                    val adapter = build.adapter<List<Person>>(listOfPersonsType)
//                    val persons = adapter.fromJson(json)
                    val persons = moshiToMutableList(Person::class.java, json2)

                    logE("moshiToMutableList persons:${persons[0].name}")

                }
            }
        }
    }

    fun moshiToJson(any: Any): String {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(any.javaClass)
        return adapter.toJson(any)
    }

    fun <T> moshiToAny(clazz: Class<T>, json: String): T {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(clazz)
        return adapter.fromJson(json) as T
    }

    fun <T> moshiToList(beanClass: Class<T>, json: String): List<T> {
        val listOfPersonsType = Types.newParameterizedType(List::class.java, beanClass)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<T>>(listOfPersonsType)
        return adapter.fromJson(json) as List<T>
    }

    fun <T> moshiToMutableList(beanClass: Class<T>, json: String): MutableList<T> {
        val listOfPersonsType = Types.newParameterizedType(MutableList::class.java, beanClass)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<T>>(listOfPersonsType)
        return adapter.fromJson(json) as MutableList<T>
    }


}