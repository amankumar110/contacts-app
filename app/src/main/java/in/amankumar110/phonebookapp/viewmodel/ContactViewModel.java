package in.amankumar110.phonebookapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.models.ContactsRepository;


@HiltViewModel
public class ContactViewModel extends ViewModel {



    private ContactsRepository repository;

    @Inject
    public ContactViewModel(ContactsRepository repository) {
        this.repository = repository;
    }

    public ContactViewModel(){}

    public LiveData<List<Contact>> getContacts() {
        return repository.getContacts();
    }

    public void addContact(Contact contact) {
        repository.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        repository.deleteContact(contact);
    }

}
