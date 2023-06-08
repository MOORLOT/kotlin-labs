package com.example.lab3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AudioListAdapter(
    private var audioFiles: List<AudioFile>
) : RecyclerView.Adapter<AudioListAdapter.AudioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_audio_file, parent, false)
        return AudioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audioFile = audioFiles[position]
        holder.bind(audioFile)
    }

    override fun getItemCount(): Int {
        return audioFiles.size
    }

    fun setAudioFiles(files: List<AudioFile>) {
        audioFiles = files
        notifyDataSetChanged()
    }

    inner class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val context: Context = itemView.context

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(audioFile: AudioFile) {
            titleTextView.text = audioFile.title
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val audioFile = audioFiles[position]
                val intent = Intent(context, AudioPlayerActivity::class.java)
                intent.putExtra("audioFileUri", audioFile.filePath)
                context.startActivity(intent)
            }
        }
    }
}



