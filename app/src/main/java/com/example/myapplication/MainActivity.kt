package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupDrawerContent(navigation_view);

        if(savedInstanceState != null) {

            rick_and_morty_title.setTextColor(savedInstanceState.getInt("colorTitleRick"))
            south_park_title.setTextColor(savedInstanceState.getInt("colorTitleSouth"))
            nachalo_title.setTextColor(savedInstanceState.getInt("colorTitleNachalo"))

        }

        setAnimationImage("In")

    }

    private fun setupDrawerContent(navigationView: NavigationView?) {
        navigationView?.setNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.nav_exit -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.question_do_you_want_leave))
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.answer_yes)){ dialogInterface: DialogInterface, id: Int -> finish()}
                    .setNegativeButton(getString(R.string.answer_no)) { dialogInterface: DialogInterface, id: Int -> }.show()
                true
            }
                else -> true
            }
        }
    }


    override fun onResume() {
        super.onResume()
        setAnimationImage("In")
    }

    private fun setAnimationImage(operation: String, image: ImageView? = null){

        if(operation == "In"){
            val anamationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            rick_and_morty_image.startAnimation(anamationIn)
            nachalo_image.startAnimation(anamationIn)
            south_park_image.startAnimation(anamationIn)
        }
        else if(operation == "Out"){
            val anamationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            image?.startAnimation(anamationOut)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            R.id.invite_friend -> {
                val intent = Intent()
                intent.action = ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.how_do_you_like_my_app))
                intent.type = "text/plain"

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
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
        outState.putInt("colorTitleRick", rick_and_morty_title.currentTextColor)
        outState.putInt("colorTitleSouth", south_park_title.currentTextColor)
        outState.putInt("colorTitleNachalo", nachalo_title.currentTextColor)
    }

    fun rickAndMortyClick(view: View) {
        rick_and_morty_title.setTextColor(Color.BLUE)
        val fullDescriptionIntent = Intent(this,FullDescription::class.java)
        fullDescriptionIntent.putExtra("description",rick_and_morty_description.text)
        fullDescriptionIntent.putExtra("image", R.drawable.o283ebd3feb35on61i47j)
        setAnimationImage("Out", rick_and_morty_image)
        startActivityForResult(fullDescriptionIntent,404)
    }

    fun southParkClick(view: View) {
        south_park_title.setTextColor(Color.BLUE)
        val fullDescriptionIntent = Intent(this,FullDescription::class.java)
        fullDescriptionIntent.putExtra("description",south_park_description.text)
        fullDescriptionIntent.putExtra("image",R.drawable.south_park_min)
        setAnimationImage("Out", south_park_image)
        startActivityForResult(fullDescriptionIntent,404)
    }

    fun nachaloClick(view: View) {
        nachalo_title.setTextColor(Color.BLUE)
        val fullDescriptionIntent = Intent(this,FullDescription::class.java)
        fullDescriptionIntent.putExtra("description",nachalo_description.text)
        fullDescriptionIntent.putExtra("image",R.drawable.nachalo)
        setAnimationImage("Out", nachalo_image)
        startActivityForResult(fullDescriptionIntent,404)
    }

    fun addMovie(view: View) {
       val activityFragment =  this.layoutInflater.inflate(R.layout.activity_fragment_movie, null, false)
        container.addView(activityFragment, container.childCount - 1)
    }



}
