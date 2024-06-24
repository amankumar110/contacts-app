package in.amankumar110.phonebookapp.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.amankumar110.phonebookapp.databinding.ContactItemLayoutBinding;
import in.amankumar110.phonebookapp.databinding.EmptyListItemLayoutBinding;
import in.amankumar110.phonebookapp.databinding.PhoneBookTitleLayoutItemBinding;
import in.amankumar110.phonebookapp.eventListeners.ContactItemEventListeners;
import in.amankumar110.phonebookapp.models.Contact;

public class SearchContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_CONTACT = 1;
    public static final int VIEW_TYPE_EMPTY = 2;
    private List<Contact> contactList;
    private FragmentManager fragmentManager;

    public SearchContactsAdapter(List<Contact> contactList, FragmentManager fragmentManager) {
        this.contactList = contactList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {

        return contactList.isEmpty() ? VIEW_TYPE_EMPTY : VIEW_TYPE_CONTACT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     if (viewType == VIEW_TYPE_CONTACT)
            return new ContactViewHolder(
                    ContactItemLayoutBinding
                            .inflate(LayoutInflater.from(parent.getContext()),
                                    parent,
                                    false));
        else
            return new EmptyViewHolder(
                    EmptyListItemLayoutBinding
                            .inflate(LayoutInflater.from(parent.getContext()),
                                    parent,
                                    false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ContactViewHolder) {

            Contact currentContact = contactList.get(position);
            ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
            contactViewHolder.binding.setListeners(new ContactItemEventListeners(fragmentManager,currentContact));
            contactViewHolder.binding.setContact(currentContact);
        }

    }

    @Override
    public int getItemCount() {
            return contactList.isEmpty() ? 1 : contactList.size();
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        ContactItemLayoutBinding binding;
        public ContactViewHolder(ContactItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        EmptyListItemLayoutBinding binding;
        public EmptyViewHolder(EmptyListItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
