package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_full_description.*

class FullDescription : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_description)
        description_full.text = intent.getStringExtra("description")
        image_full.setImageResource(intent.getIntExtra("image",0))
        bottom_sheet_description.text = intent.getStringExtra("description")
    }


    override fun onBackPressed() {
        val intentForResult =  Intent()
        intentForResult.putExtra("comment",comment.text.toString())
        setResult(RESULT_OK, intentForResult);
        finish()
        super.onBackPressed()
    }

    fun favoriteButton(view: View) {
        Toast.makeText(applicationContext, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
    }


}
