package in.amankumar110.phonebookapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import dagger.hilt.android.AndroidEntryPoint;
import in.amankumar110.phonebookapp.databinding.ActivityAddContactBinding;
import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.utils.ValidationManager;
import in.amankumar110.phonebookapp.viewmodel.ContactViewModel;

@AndroidEntryPoint
public class AddContactActivity extends AppCompatActivity {

   private ActivityAddContactBinding binding;
   Contact contact;
   ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        contact = new Contact();
        binding.setContact(contact);

        binding.addContactButton.setOnClickListener(v -> {

            Log.v("contact",contact.toString());
            ValidationManager validationManager = new ValidationManager(TextInputLayout::setError);

            validationManager.validateName(binding.contactNameTextLayout);
            validationManager.validateEmailAddress(binding.contactEmailTextLayout);
            validationManager.validatePhoneNumber(binding.contactPhoneNumberTextLayout);
            validationManager.validateHomeNumber(binding.contactHomeNumberTextLayout);

            if(validationManager.isAllValid()) {

                viewModel.addContact(contact);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Success!");
                alertDialog.setMessage(contact.getName()+" added to contacts");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("OK",(dialog, which) -> {
                    dialog.dismiss();
                    startActivity(new Intent(this, MainActivity.class));
                });

                Dialog dialog = alertDialog.create();
                dialog.show();

            }

        });

        setOnEditTextFocused(binding.contactNameTextLayout.getEditText(),binding.contactNameTextLayout);
        setOnEditTextFocused(binding.contactEmailTextLayout.getEditText(),binding.contactEmailTextLayout);
        setOnEditTextFocused(binding.contactPhoneNumberTextLayout.getEditText(),binding.contactPhoneNumberTextLayout);
        setOnEditTextFocused(binding.contactHomeNumberTextLayout.getEditText(),binding.contactHomeNumberTextLayout);
    }

    public void setOnEditTextFocused(EditText editText,TextInputLayout textInputLayout){

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (textInputLayout.isErrorEnabled())
                textInputLayout.setErrorEnabled(false);
        });

    }



}