package com.example.lab3

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.security.AccessControlContext
import java.security.AccessController.getContext

class AudioPlayerActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: ImageButton
    private lateinit var progressSeekBar: SeekBar
    private lateinit var songTitleTextView: TextView
    private lateinit var artistTextView: TextView

    private var audioFileUri: String? = null // Replace with the actual URI of the audio file

    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        mediaPlayer = MediaPlayer()

        playButton = findViewById(R.id.playButton)
        progressSeekBar = findViewById(R.id.progressSeekBar)
        songTitleTextView = findViewById(R.id.songTitleTextView)
        artistTextView = findViewById(R.id.artistTextView)

        audioFileUri = intent.getStringExtra("audioFileUri")

        // Set initial values for the views
        songTitleTextView.text = intent.getStringExtra("audioFileUri")
        artistTextView.text = "Artist"

        // Set up click listener for the play button
        playButton.setOnClickListener {
            if (isPlaying) {
                pauseAudio()
            } else {
                startAudio()
            }
        }

        // Set up progressSeekBar change listener
        progressSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Handle progress changes
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when the user starts interacting with the seek bar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when the user stops interacting with the seek bar
            }
        })
    }

    private fun startAudio() {
        try {
            mediaPlayer.setDataSource(audioFileUri)
            mediaPlayer.prepare()
            mediaPlayer.start()
            isPlaying = true

            // Update the play button icon
            playButton.setImageResource(R.drawable.baseline_pause_24)
        } catch (e: Exception) {
            // Log the exception for debugging purposes
            Log.e("AudioPlayerActivity", "Error playing audio", e)

            // Handle the exception (e.g., display an error message)
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pauseAudio() {
        mediaPlayer.pause()
        isPlaying = false

        // Update the play button icon
        playButton.setImageResource(R.drawable.baseline_play_arrow_24)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
