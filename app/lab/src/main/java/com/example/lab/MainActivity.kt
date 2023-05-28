package com.example.lab

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var personAdapter: PersonAdapter? = null
    private var addButton: FloatingActionButton? = null
    private var removeButton: FloatingActionButton? = null
    private var idToRemove: String? = null
    private var name: String? = null
    private var data: String? = null
    private var counter: Int? = null
    private var editPersonID: String? = null
    private var editPersonName: String? = null
    private var editPersonData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val persons = PersonStorage.rad(this)

        personAdapter = PersonAdapter(persons)
        recyclerView.adapter = personAdapter

        addButton = findViewById(R.id.fab)
        addButton?.setOnClickListener(getNewPersonFromActivity)

        removeButton = findViewById(R.id.fab1)
        removeButton?.setOnClickListener(getIdFromActivity)

        counter = persons.size - 1
    }

    private val getNewPersonFromActivity = View.OnClickListener {
        val intent = Intent(this@MainActivity, AddPersonActivity::class.java)
        getNameActivityResult.launch(intent)
    }

    private val getNameActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                name = it.data?.getStringExtra("name")
                data = it.data?.getStringExtra("data")
                val lastPersonID = counter!! + 1
                val newPerson = Person(
                    id = lastPersonID,
                    avatar = AvatarStore.random(),
                    data = data.toString(),
                    name = name.toString()
                )
                personAdapter?.addPerson(newPerson)
                Toast.makeText(this, "ID $lastPersonID", Toast.LENGTH_SHORT).show()
                counter = personAdapter?.itemCount
            }
        }

    private val getIdFromActivity = View.OnClickListener {
        val intent = Intent(this@MainActivity, RemovePerson::class.java)
        getIDtoRemoveActivity.launch(intent)
    }

    private fun removePersonById(personId: Int) {
        val persons = personAdapter?.items
        if (persons != null) {
            val personToRemove = persons.find { it.id == personId }
            if (personToRemove != null) {
                personAdapter?.removePerson(personToRemove)
            }
        }
    }

    private val getIDtoRemoveActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                idToRemove = it.data?.getStringExtra("removeID")
                removePersonById(idToRemove!!.toInt())
                Toast.makeText(this, "Removed ID $idToRemove", Toast.LENGTH_SHORT).show()
                counter = personAdapter?.itemCount
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ITEM_DETAILS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            editPersonID = data?.getStringExtra("editPersonID")
            editPersonName = data?.getStringExtra("editPersonName")
            editPersonData = data?.getStringExtra("editPersonData")
            Toast.makeText(this, "Edit ID $editPersonID", Toast.LENGTH_SHORT).show()
            val editedPerson = personAdapter?.items?.find { it.id == editPersonID!!.toInt() }
            editedPerson?.name = editPersonName.toString()
            editedPerson?.data = editPersonData.toString()
            personAdapter?.notifyDataSetChanged()
        }
    }
    companion object {
        const val ITEM_DETAILS_REQUEST_CODE = 1

    }
}

