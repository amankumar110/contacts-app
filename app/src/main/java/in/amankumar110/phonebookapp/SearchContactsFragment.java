package in.amankumar110.phonebookapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import in.amankumar110.phonebookapp.databinding.FragmentSearchContactsBinding;
import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.view.ContactAdapter;
import in.amankumar110.phonebookapp.view.SearchContactsAdapter;
import in.amankumar110.phonebookapp.viewmodel.ContactViewModel;
import kotlin.collections.ArrayDeque;


public class SearchContactsFragment extends Fragment {

    private FragmentSearchContactsBinding binding;

    private SearchContactsAdapter adapter;
    private ContactViewModel viewModel;
    private List<Contact> contactList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchContactsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
        contactList = new ArrayList<>();

        adapter = new SearchContactsAdapter(contactList,getParentFragmentManager());
        binding.contactsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.contactsList.setAdapter(adapter);

        viewModel.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            contactList.clear();
            contactList.addAll(contacts);
            adapter.notifyDataSetChanged();
        });

        binding.searchEditText.addTextChangedListener(onSearchTextChanged);

    }

    private TextWatcher onSearchTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {

            String query = s.toString();

            if (query.trim().isEmpty()) {
                adapter.setContactList(contactList);
            } else {

                List<Contact> filteredContacts = contactList.stream().filter(contact -> {
                    return contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                            contact.getPhoneNumber().contains(query);
                }).collect(Collectors.toList());

                adapter.setContactList(filteredContacts);
            }
        }
    };

}