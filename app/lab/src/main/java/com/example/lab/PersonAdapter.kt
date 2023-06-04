package com.example.lab

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lab.MainActivity.Companion.ITEM_DETAILS_REQUEST_CODE

class PersonAdapter(val items: MutableList<Person>) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    private var listener: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_element, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setOnItemClickListener(mainActivity: MainActivity) {
        this.listener = listener
    }

    fun addPerson(person: Person) {
        items.add(person)
        notifyItemInserted(items.size - 1)
    }

    fun removePerson(person: Person) {
        val index = items.indexOf(person)
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val personID = itemView.findViewById<TextView>(R.id.editTextID)
        val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        val name = itemView.findViewById<TextView>(R.id.name)
        val data = itemView.findViewById<TextView>(R.id.stats)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(chat: Person) {
            personID.text = "ID: ${chat.id.toString()}"
            avatar.setImageResource(chat.avatar)
            name.text = chat.name
            data.text = chat.data
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedPerson = items[position]
                val intent = Intent(view.context, ItemDetailsActivity::class.java)
                intent.putExtra("personID", clickedPerson.id.toString())
                intent.putExtra("personName", clickedPerson.name)
                intent.putExtra("personData", clickedPerson.data)
                (view.context as Activity).startActivityForResult(intent, ITEM_DETAILS_REQUEST_CODE)
            }
        }
    }

}