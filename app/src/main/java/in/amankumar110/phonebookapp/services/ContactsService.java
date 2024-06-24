package in.amankumar110.phonebookapp.services;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.amankumar110.phonebookapp.models.Contact;

@Dao
public interface ContactsService {


    @Query("SELECT * FROM contacts_table")
    public LiveData<List<Contact>> getContacts();

    @Insert
    public void addContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

}
