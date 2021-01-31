package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class BottomSheet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
    }

    fun favoriteButton(view: View) {
        Toast.makeText(applicationContext, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()//
    }


}
