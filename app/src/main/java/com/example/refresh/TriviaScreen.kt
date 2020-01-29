package com.example.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class TriviaScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_screen)
        getTrivia()
    }

    fun getTrivia() {
        val number = findViewById<TextView>(R.id.number)
        val fact = findViewById<TextView>(R.id.fact)
        val spinner = findViewById<ProgressBar>(R.id.progressBarTrivia)

        val queue = Volley.newRequestQueue(this)
        val url = "http://numbersapi.com/random/trivia"
        val stringReuest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->

//                val gsonBuilder = GsonBuilder()
//                val gson = gsonBuilder.create()
//                val joke = gson.fromJson(response, Joke::class.java)

                spinner.visibility = View.GONE

                number.visibility = View.VISIBLE
                fact.visibility = View.VISIBLE

                number.setText(response.split("\\s".toRegex())[0])
                fact.setText(response)

            }, Response.ErrorListener { error ->
                spinner.visibility = View.GONE

                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(stringReuest)
    }
    fun onClickRefresh(view: View) {
        val number = findViewById<TextView>(R.id.number)
        val fact = findViewById<TextView>(R.id.fact)
        val spinner = findViewById<ProgressBar>(R.id.progressBarTrivia)

        spinner.visibility = View.VISIBLE
        number.visibility = View.GONE
        fact.visibility = View.GONE
        getTrivia()
    }
    fun goToHomeScreen(view: View) {
        finish()
    }
}
