package com.example.numad24fa_jiachenliang;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class ContactsCollectorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private ArrayList<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_collector);

        contactList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsAdapter = new ContactsAdapter(contactList, this::startPhoneCall, this::editContact, this::deleteContact);
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> showAddContactDialog());
    }

    private void showAddContactDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_contact, null);
        EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        EditText phoneEditText = dialogView.findViewById(R.id.phoneEditText);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Add Contact")
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = nameEditText.getText().toString().trim();
                    String phone = phoneEditText.getText().toString().trim();
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
                        Contact contact = new Contact(name, phone);
                        addContact(contact);
                    } else {
                        Snackbar.make(recyclerView, "Name and phone number cannot be empty", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create().show();
    }

    private void addContact(Contact contact) {
        contactList.add(contact);
        contactsAdapter.notifyItemInserted(contactList.size() - 1);
        Snackbar.make(recyclerView, "Contact added", Snackbar.LENGTH_LONG)
                .setAction("Undo", v -> {
                    contactList.remove(contact);
                    contactsAdapter.notifyDataSetChanged();
                }).show();
    }

    private void startPhoneCall(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhoneNumber()));
        startActivity(intent);
    }

    private void editContact(Contact contact) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_contact, null);
        EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        EditText phoneEditText = dialogView.findViewById(R.id.phoneEditText);

        // Pre-fill the fields with the current contact's details
        nameEditText.setText(contact.getName());
        phoneEditText.setText(contact.getPhoneNumber());

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Edit Contact")
                .setPositiveButton("Save", (dialog, which) -> {
                    String newName = nameEditText.getText().toString().trim();
                    String newPhone = phoneEditText.getText().toString().trim();
                    if (!TextUtils.isEmpty(newName) && !TextUtils.isEmpty(newPhone)) {
                        // Update contact details
                        contact.setName(newName);
                        contact.setPhoneNumber(newPhone);
                        contactsAdapter.notifyDataSetChanged();
                        Snackbar.make(recyclerView, "Contact updated", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(recyclerView, "Name and phone number cannot be empty", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create().show();
    }

    private void deleteContact(Contact contact) {
        int index = contactList.indexOf(contact);
        if (index != -1) {
            contactList.remove(index);
            contactsAdapter.notifyItemRemoved(index);
            Snackbar.make(recyclerView, "Contact deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo", v -> {
                        contactList.add(index, contact);
                        contactsAdapter.notifyItemInserted(index);
                    })
                    .show();
        }
    }
}
