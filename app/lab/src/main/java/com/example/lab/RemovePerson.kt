package com.example.lab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class RemovePerson : AppCompatActivity() {
    private var editTextID: EditText? = null
    private var removeButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_person)

        editTextID = findViewById(R.id.editTextID)
        removeButton = findViewById(R.id.removeButton)
        removeButton?.setOnClickListener(getRemoveID)
    }

    private val getRemoveID = View.OnClickListener {
        val intent = Intent()
        intent.putExtra("removeID", editTextID?.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}