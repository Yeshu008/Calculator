package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.ln

class MainActivity : AppCompatActivity() {
    private var tvinput:TextView?=null
    var lnum:Boolean=false
    var ldot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput=findViewById(R.id.tvid)
    }
    fun onDigit(view:View) {

        tvinput?.append((view as Button).text)
        lnum=true
        ldot=false
        //Toast.makeText(this,"Button clicked",Toast.LENGTH_LONG).show()
    }
    fun onclr(view: View)
    {
        tvinput?.text=""
    }
    fun onDeci(view: View)
    {
        if(lnum && !ldot)
        {
            tvinput?.append(".")
            lnum=false
            ldot=true
        }
    }
    fun onop(view: View)
    {
        tvinput?.text?.let {
            if(lnum && !isop(it.toString())){
                tvinput?.append((view as Button).text)
                lnum=false
                ldot=false
            }
        }
    }
    fun oneq(view: View)
    {
        if(lnum){
            var tvval=tvinput?.text.toString()
            var prefix=""
            try{
                if(tvval.startsWith("-"))
                {
                    prefix="-"
                    tvval=tvval.substring(1)
                }
                if(tvval.startsWith("-"))
                {
                    val slp=tvval.split("-")
                    var one=slp[0]
                    var two=slp[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvinput?.text=rdot((one.toDouble()-two.toDouble()).toString())
                }
                else if(tvval.contains("+"))
                {
                    val slp=tvval.split("+")
                    var one=slp[0]
                    var two=slp[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvinput?.text=rdot((one.toDouble()+two.toDouble()).toString())
                }
                else if(tvval.contains("/"))
                {
                    val slp=tvval.split("/")
                    var one=slp[0]
                    var two=slp[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvinput?.text=rdot((one.toDouble()/two.toDouble()).toString())
                }
                else if(tvval.contains("*"))
                {
                    val slp=tvval.split("*")
                    var one=slp[0]
                    var two=slp[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvinput?.text=rdot((one.toDouble()*two.toDouble()).toString())
                }

            }
            catch (e:ArithmeticException){
                    e.printStackTrace()
            }

        }
    }

    private fun rdot(result: String):String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)

        return value
    }

    private fun isop(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }

}