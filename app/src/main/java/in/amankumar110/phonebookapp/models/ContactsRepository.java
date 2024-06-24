package in.amankumar110.phonebookapp.models;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import in.amankumar110.phonebookapp.services.ContactsService;

public class ContactsRepository {

    private ContactsService service;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Inject
    public ContactsRepository(ContactsService contactsService) {
        this.service = contactsService;
    }

    public LiveData<List<Contact>> getContacts() {
        return service.getContacts();
    }
    
    public void addContact(Contact contact) {
        executor.execute(()-> service.addContact(contact));
    }

    public void deleteContact(Contact contact) {
        executor.execute(()-> service.deleteContact(contact));
    }

}
