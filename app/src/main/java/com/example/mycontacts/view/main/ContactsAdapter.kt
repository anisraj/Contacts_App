package com.example.mycontacts.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.R
import com.example.mycontacts.model.ContactData
import com.example.mycontacts.model.ContactType

class ContactsAdapter(
    var contacts : ArrayList<ContactData>,
    private val clickListener: ContactClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private var MEMBER_VALUE = 0
    private var HEADER_VALUE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            MEMBER_VALUE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_layout_contact, parent, false)
                MemberViewHolder(view)
            }
            HEADER_VALUE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.layout_sort_char, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_layout_contact, parent, false)
                MemberViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {
        if (viewholder.itemViewType == MEMBER_VALUE) {
            val holder = viewholder as MemberViewHolder
            val contact = contacts[position]
            holder.tvName.text = contact.name
            holder.tvNumber.text = contact.mobileNumber
            holder.itemView.setOnClickListener {
                clickListener.onClick(contact)
            }
        } else if (viewholder.itemViewType == HEADER_VALUE) {
            val holder = viewholder as HeaderViewHolder
            holder.tvChar.text = contacts[position].char
        }

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (contacts[position].type == ContactType.MEMBER) {
            MEMBER_VALUE
        } else {
            HEADER_VALUE
        }
    }

    inner class MemberViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tvName: TextView = itemview.findViewById<TextView>(R.id.tvName)
        var tvNumber: TextView = itemview.findViewById<TextView>(R.id.tvMobileNumber)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvChar: TextView = itemView.findViewById(R.id.tvSortChar)
    }
}