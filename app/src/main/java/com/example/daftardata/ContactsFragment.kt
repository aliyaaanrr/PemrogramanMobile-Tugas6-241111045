package com.example.daftardata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class ContactsFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ItemAdapter
    private var contacts = mutableListOf<Item>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        
        dbHelper = DatabaseHelper(requireContext())
        
        val rvContacts: RecyclerView = view.findViewById(R.id.rvContacts)
        val btnAddContact: MaterialButton = view.findViewById(R.id.btnAddContact)
        
        rvContacts.layoutManager = LinearLayoutManager(context)
        
        loadContacts()
        
        adapter = ItemAdapter(contacts) { clickedItem ->
            val bundle = Bundle().apply {
                putString("CONTACT_NAME", clickedItem.title)
                putString("CONTACT_PHONE", clickedItem.description)
            }
            findNavController().navigate(R.id.action_contacts_to_detail, bundle)
        }
        rvContacts.adapter = adapter
        
        btnAddContact.setOnClickListener {
            showAddContactDialog()
        }
        
        return view
    }

    private fun loadContacts() {
        contacts.clear()
        contacts.addAll(dbHelper.getAllContacts())
    }

    private fun showAddContactDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tambah Kontak")
        
        val view = layoutInflater.inflate(R.layout.dialog_add_contact, null)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etPhone = view.findViewById<EditText>(R.id.etPhone)
        
        builder.setView(view)
        builder.setPositiveButton("Tambah") { _, _ ->
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                dbHelper.addContact(name, phone)
                loadContacts()
                adapter.notifyDataSetChanged()
                Toast.makeText(context, "Kontak berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Batal", null)
        builder.show()
    }
}
