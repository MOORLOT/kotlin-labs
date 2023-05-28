package com.example.lab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItemDetailsActivity : AppCompatActivity(){
    private var editTextData: EditText? = null
    private var editTextName: EditText? = null
    private var editButton: Button? = null
    private var editPersonID: String? = null
    private var itemName: String? = null
    private var itemData: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_info)

        editTextName = findViewById(R.id.editTextName)
        editTextData = findViewById(R.id.editTextData)
        editButton = findViewById(R.id.editButton)
        editButton?.setOnClickListener(getData)

        editPersonID = intent.getStringExtra("personID")
        itemName = intent.getStringExtra("personName")
        itemData = intent.getStringExtra("personData")
        Toast.makeText(this, "Intent ID $editPersonID $itemName $itemData", Toast.LENGTH_SHORT).show()
        editTextName?.setText(itemName)
        editTextData?.setText(itemData)
    }

    private val getData = View.OnClickListener {
        editPersonID = intent.getStringExtra("personID")

        val intent = Intent()
        intent.putExtra("editPersonID", editPersonID)
        intent.putExtra("editPersonName", editTextName?.text.toString())
        intent.putExtra("editPersonData", editTextData?.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}