package com.example.refresh

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quickGraph = findViewById<ImageView>(R.id.quick_graph)

        val c = Calendar.getInstance()
        val dateformat = SimpleDateFormat("HH")
        val datetime: String = dateformat.format(c.time)

        var timeInFloat = datetime.toFloat()
        var percentage:Float = 100-((timeInFloat/24.0f)*100)
        var data = percentage.toInt().toString()

        val url = "https://quickchart.io/chart?c={type:'radialGauge',data:{datasets:[{data:[$data],backgroundColor:'rgb(148,0,211)'}]}}"
        Picasso.get().load(url).into(quickGraph);

    }
    fun goToJokesScreen(view: View) {
        val intent = Intent(this,JokesScreen::class.java)
        startActivity(intent)
    }

    fun goToBoredScreen(view: View) {
        val intent = Intent(this,BoredScreen::class.java)
        startActivity(intent)
    }

    fun goToTriviaScreen(view: View) {
        val intent = Intent(this,TriviaScreen::class.java)
        startActivity(intent)
    }

    fun goToQuotesScreen(view: View) {
        val intent = Intent(this,QuotesScreen::class.java)
        startActivity(intent)
    }

    fun goToPicsumScreen(view: View) {
        val intent = Intent(this,PicsumScreen::class.java)
        startActivity(intent)
    }
    fun goToNamoScreen(view: View) {
        val intent = Intent(this,NamoScreen::class.java)
        startActivity(intent)
    }
}
