package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if(savedInstanceState != null) {
            rick_and_morty_title.setTextColor(savedInstanceState.getInt("colorTitleRick"))
            south_park_title.setTextColor(savedInstanceState.getInt("colorTitleSouth"))
            nachalo_title.setTextColor(savedInstanceState.getInt("colorTitleNachalo"))
        }

        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==404) {
            if (resultCode == RESULT_OK) {
                Log.e("OTUS", "Comment " + data?.getStringExtra("comment"))
                Log.e("OTUS", "Like " + data?.getBooleanExtra("like", false))
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("colorTitleRick",rick_and_morty_title.currentTextColor)
        outState.putInt("colorTitleSouth",south_park_title.currentTextColor)
        outState.putInt("colorTitleNachalo",nachalo_title.currentTextColor)
    }

    fun rickAndMortyClick(view: View) {
        rick_and_morty_title.setTextColor(Color.BLUE)
        val fullDescriptionIntent = Intent(this,FullDiscription::class.java)
        fullDescriptionIntent.putExtra("description",rick_and_morty_description.text)
        fullDescriptionIntent.putExtra("image",R.drawable.o283ebd3feb35on61i47j)
        startActivityForResult(fullDescriptionIntent,404)
    }

    fun southParkClick(view: View) {
        south_park_title.setTextColor(Color.BLUE)
        val fullDescriptionIntent = Intent(this,FullDiscription::class.java)
        fullDescriptionIntent.putExtra("description",south_park_description.text)
        fullDescriptionIntent.putExtra("image",R.drawable.south_park_min)
        startActivityForResult(fullDescriptionIntent,404)
    }
    fun nachaloClick(view: View) {
        nachalo_title.setTextColor(Color.BLUE)
        val fullDescriptionIntent = Intent(this,FullDiscription::class.java)
        fullDescriptionIntent.putExtra("description",nachalo_description.text)
        fullDescriptionIntent.putExtra("image",R.drawable.nachalo)
        startActivityForResult(fullDescriptionIntent,404)
    }
}
