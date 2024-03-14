package com.example.mycontacts.data.models.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.databinding.ItemContactBinding
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.databinding.DialogAddContactBinding

class ContactAdapter(
    var items:MutableList<Contact> = mutableListOf(),
    val onClickListener: (position:Int) -> Unit
    //val onAddContactListener: (position:Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
        /*holder.binding.setOnClickListener {
            onAddContactListener(position)
        }*/
    }

    fun updateItems(results: MutableList<Contact>) {
        items = results
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(val binding:ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(contact: Contact) {
            //ItemContactBinding.contactNameEditText = contact.name
            //binding.doneCheckBox.isChecked = task.done
            binding.contactName.text = contact.name
            binding.contactPhone.text = contact.phone
            binding.contactEmail.text = contact.email
        }
    }
}