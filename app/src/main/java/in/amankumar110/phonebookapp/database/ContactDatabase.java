package in.amankumar110.phonebookapp.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.services.ContactsService;

@Database(entities = {Contact.class},version =2)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase dbInstance = null;
    public abstract ContactsService getContactsService();

    public static ContactDatabase getInstance(Context context){

        if(dbInstance == null){
            dbInstance = Room
                    .databaseBuilder(context,ContactDatabase.class,"contact_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
