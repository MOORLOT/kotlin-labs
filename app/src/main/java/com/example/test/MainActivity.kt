package com.example.test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var name: String? = null
    private var age: Int? = null
    private var payment: Int? = null
    private var button: Button? = null
    private var seek: SeekBar? = null
    private var group_zero: RadioGroup? = null
    private var checkBox_0: CheckBox? = null
    private var checkBox_1: CheckBox? = null
    private var checkBox_2: CheckBox? = null
    private var counter: Int = 0
    private var nameE: EditText? = null
    private var ageE: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActivityItems()

        group_zero?.setOnCheckedChangeListener { _, checked ->
            if (checked == R.id.group_zero_02) {
                counter += 2
            }
        }

        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar, progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(
                    this@MainActivity,
                    "Payment is: " + seek.progress + "USD",
                    Toast.LENGTH_SHORT
                ).show()
                payment = seek.progress
            }
        })
    }

    private fun initActivityItems() {
        button = findViewById(R.id.ok_button)
        nameE = findViewById(R.id.name)
        ageE = findViewById(R.id.age)
        group_zero = findViewById(R.id.group_zero)
        checkBox_0 = findViewById(R.id.check_exp)
        checkBox_1 = findViewById(R.id.check_trips)
        checkBox_2 = findViewById(R.id.check_team_skills)
        seek = findViewById(R.id.payment)
        button?.setOnClickListener(getTestAnswers)
        checkBox_0?.setOnClickListener(getCheckEXP)
        checkBox_1?.setOnClickListener(getCheckTrips)
        checkBox_2?.setOnClickListener(getCheckSkill)
    }

    private val getCheckEXP = View.OnClickListener {
        if (it is CheckBox) {
            val checked: Boolean = it.isChecked
            when (it.id) {
                R.id.check_exp -> {
                    if (checked) {
                        counter += 2
                    }
                }
            }
        }
    }

    private val getCheckTrips = View.OnClickListener {
        if (it is CheckBox) {
            val checked: Boolean = it.isChecked
            when (it.id) {
                R.id.check_trips -> {
                    if (checked) {
                        counter += 1
                    }
                }
            }
        }
    }

    private val getCheckSkill = View.OnClickListener {
        if (it is CheckBox) {
            val checked: Boolean = it.isChecked
            when (it.id) {
                R.id.check_team_skills -> {
                    if (checked) {
                        counter += 1
                    }
                }
            }
        }
    }

    private val getTestAnswers = View.OnClickListener {

        if (nameE?.text.toString().trim().isEmpty() && ageE?.text.toString().trim().isEmpty()) {
            Toast.makeText(
                this@MainActivity,
                "Name or Age is empty",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            name = nameE?.text.toString()
            val value: String = ageE?.text.toString()
            age = value.toInt()

            when (it?.id) {
                R.id.ok_button -> {
                    if (counter >= 10) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://developer.android.com/")
                            )
                        )
                        counter = 0
                    } else {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://developer.android.com/games")
                            )
                        )
                        counter = 0
                    }
                }
            }
        }
    }
}