package com.example.numad24fa_jiachenliang;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private ArrayList<Contact> contacts;
    private OnContactClickListener callListener;
    private OnContactClickListener editListener;
    private OnContactClickListener deleteListener;

    // Define interfaces for click actions
    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    // Updated constructor to accept the action listeners
    public ContactsAdapter(ArrayList<Contact> contacts, OnContactClickListener callListener,
                           OnContactClickListener editListener, OnContactClickListener deleteListener) {
        this.contacts = contacts;
        this.callListener = callListener;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());

        // Set up the click listener for making a call
        holder.itemView.setOnClickListener(v -> callListener.onContactClick(contact));

        // Set up a long-click listener to show options for Edit or Delete
        holder.itemView.setOnLongClickListener(v -> {
            // Show an AlertDialog with Edit and Delete options
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Contact Options")
                    .setItems(new CharSequence[]{"Edit", "Delete"}, (dialog, which) -> {
                        if (which == 0) {
                            // Edit option selected
                            editListener.onContactClick(contact);
                        } else if (which == 1) {
                            // Delete option selected
                            deleteListener.onContactClick(contact);
                        }
                    })
                    .show();
            return true;
        });
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, phoneTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contact_name);
            phoneTextView = itemView.findViewById(R.id.contact_phone);
        }
    }
}
