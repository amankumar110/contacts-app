package in.amankumar110.phonebookapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.amankumar110.phonebookapp.databinding.FragmentContactListBinding;
import in.amankumar110.phonebookapp.eventListeners.FragmentContactListEventListeners;
import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.view.ContactAdapter;
import in.amankumar110.phonebookapp.viewmodel.ContactViewModel;

public class ContactListFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private FragmentContactListBinding binding;
    private List<Contact> contactList;
    private Context context;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactList = new ArrayList<>(); // Initialize contactList
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up RecyclerView
        binding.contactRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ContactAdapter adapter = new ContactAdapter(contactList, getParentFragmentManager(),()->{

            getParentFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                    .replace(R.id.fragment_container,new SearchContactsFragment())
                    .commit();
        });

        binding.contactRecyclerView.setAdapter(adapter);

        // Set click listener for add contact button
        binding.addContactButton.setOnClickListener(v -> {
            requireContext().startActivity(new Intent(requireActivity(), AddContactActivity.class));
        });

        // Initialize ViewModel
        contactViewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);


        // Observe LiveData from ViewModel
        contactViewModel.getContacts().observe(requireActivity(),contacts ->{
            this.contactList.clear();
            contactList.addAll(contacts);
            adapter.notifyDataSetChanged();
        });
    }


}
