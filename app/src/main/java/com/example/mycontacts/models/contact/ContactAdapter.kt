package com.example.mycontacts.models.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.databinding.ItemContactBinding

class ContactAdapter(
    var items:MutableList<Contact> = mutableListOf(),
    val onDeleteItemListener: (position:Int) -> Unit,
    val onItemClickListener: (position:Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.render(items[position])
        holder.binding.deleteContactButton.setOnClickListener { onDeleteItemListener(position) }
        holder.binding.contactFieldsCardView.setOnClickListener {
            onItemClickListener(position)
        }
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