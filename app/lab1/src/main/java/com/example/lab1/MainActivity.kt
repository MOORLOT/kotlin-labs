package com.example.lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FileListFragment.OnFileSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FileListFragment())
                .commit()
        }
    }

    override fun onFileSelected(fileName: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FileDetailFragment.newInstance(fileName))
            .addToBackStack(null)
            .commit()
    }
}


