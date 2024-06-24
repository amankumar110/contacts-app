package in.amankumar110.phonebookapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.amankumar110.phonebookapp.databinding.FragmentContactBinding;
import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.viewmodel.ContactViewModel;


public class ContactFragment extends Fragment {

    private FragmentContactBinding binding;
    private Contact contact;
    private ContactViewModel viewModel;

    public ContactFragment(Contact contact) {
        this.contact = contact;
    }

    public ContactFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactBinding.inflate(inflater,container,false);
        binding.setContact(contact);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
        binding.deleteButton.setOnClickListener(this::onDeleteButtonClicked);
    }

    private void onDeleteButtonClicked(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
        alertDialog.setTitle("Do you want to delete this contact?");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Yes", (dialog, which) -> {
            viewModel.deleteContact(this.contact);
            startActivity(new Intent( requireActivity() ,MainActivity.class));
        });

        alertDialog.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        alertDialog.show();

    }

}