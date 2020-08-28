package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_full_description.*

class FullDiscription : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_description)
        description_full.text = intent.getStringExtra("description")
        image_full.setImageResource(intent.getIntExtra("image",0))
    }


    override fun onBackPressed() {
        val intentForResult =  Intent()
        intentForResult.putExtra("comment",comment.text.toString())
        //intentForResult.putExtra("like",likeIt.isChecked)
        setResult(RESULT_OK, intentForResult);
        finish()
        super.onBackPressed()
    }
}
