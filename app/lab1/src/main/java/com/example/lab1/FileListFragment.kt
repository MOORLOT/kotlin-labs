package com.example.lab1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment

class FileListFragment : Fragment() {

    private lateinit var fileListAdapter: ArrayAdapter<String>
    private var fileSelectedListener: OnFileSelectedListener? = null

    interface OnFileSelectedListener {
        fun onFileSelected(fileName: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fileSelectedListener = context as? OnFileSelectedListener
        if (fileSelectedListener == null) {
            throw ClassCastException("$context")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_file_list, container, false)
        val fileList = rootView.findViewById<ListView>(R.id.fileList)

        val files = arrayOf("Bakalov", "Artem", "Igorovych", "PMA", "mark: 5")
        fileListAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, files)
        fileList.adapter = fileListAdapter

        fileList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val fileName = files[position]
                fileSelectedListener?.onFileSelected(fileName)
            }

        return rootView
    }
}
