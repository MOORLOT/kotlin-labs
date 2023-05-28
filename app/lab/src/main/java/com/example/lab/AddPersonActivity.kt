package com.example.lab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class AddPersonActivity : AppCompatActivity() {
    private var editTextData: EditText? = null
    private var editTextName: EditText? = null
    private var addButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_person)

        editTextName = findViewById(R.id.editTextName)
        editTextData = findViewById(R.id.editTextData)
        addButton = findViewById(R.id.saveButton)
        addButton?.setOnClickListener(getData)
    }

    private val getData = View.OnClickListener {
        val intent = Intent()
        if (editTextName == null || editTextData == null){
            intent.putExtra("name",PersonDataStorage.random())
            intent.putExtra("data",NameStorage.random())
        } else {
            intent.putExtra("name", editTextName?.text.toString())
            intent.putExtra("data", editTextData?.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}