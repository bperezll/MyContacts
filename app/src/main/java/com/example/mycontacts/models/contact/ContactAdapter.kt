package com.example.mycontacts.models.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.databinding.ItemContactBinding

class ContactAdapter(
    private var items: MutableList<Contact> = mutableListOf(),
    val onDeleteItemListener: (position: Int) -> Unit,
    val onItemClickListener: (position: Int) -> Unit,
    val onEditContactClickListener: (position: Int) -> Unit,
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.render(items[position])
        holder.binding.deleteContactButton.setOnClickListener { onDeleteItemListener(position) }
        holder.binding.editContactButton.setOnClickListener { onEditContactClickListener(position) }
        holder.binding.itemContactConstraint.setOnClickListener { onItemClickListener(position) }
    }

    fun updateItems(results: MutableList<Contact>) {
        items = results
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(val binding:ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(contact: Contact) {
            binding.contactName.text = contact.name
            binding.contactPhone.text = contact.phone

            // Get first char of the first and the second word if any
            val firstNameInitial = contact.name.firstOrNull()?.toString() ?: ""
            val secondNameInitial = contact.name.split(" ").getOrNull(1)?.firstOrNull()?.toString() ?: ""
            val initials = "$firstNameInitial$secondNameInitial"
            binding.nameInitials.text = initials
        }
    }
}
