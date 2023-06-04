package com.example.lab1

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FileDetailFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_file_detail, container, false)
        progressBar = rootView.findViewById(R.id.progressBar)
        progressTextView = rootView.findViewById(R.id.progressTextView)

        val fileName = arguments?.getString(ARG_FILE_NAME)
        simulateFileDownload(fileName)

        return rootView
    }

    @SuppressLint("SetTextI18n")
    private fun simulateFileDownload(fileName: String?) {
        progressBar.progress = 0
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            var progress = 0
            while (progress < 100) {
                progress += 10
                progressBar.progress = progress
                progressTextView.text = "Progress: $progress%"
                Thread.sleep(200)
            }
            progressTextView.text = "Progress: 100% - Download Complete!"
            showFileDetails(fileName)
        }, 1000)
    }

    private fun showFileDetails(fileName: String?) {
        val fileDetailsTextView: TextView = requireView().findViewById(R.id.fileDetailsTextView)
        fileDetailsTextView.text = "File Name: $fileName"
        progressBar.visibility = View.GONE
        progressTextView.visibility = View.GONE
        fileDetailsTextView.visibility = View.VISIBLE
    }

    companion object {
        private const val ARG_FILE_NAME = "file_name"

        fun newInstance(fileName: String): FileDetailFragment {
            val fragment = FileDetailFragment()
            val args = Bundle()
            args.putString(ARG_FILE_NAME, fileName)
            fragment.arguments = args
            return fragment
        }
    }
}
