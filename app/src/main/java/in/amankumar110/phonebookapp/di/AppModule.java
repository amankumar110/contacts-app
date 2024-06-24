package in.amankumar110.phonebookapp.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import in.amankumar110.phonebookapp.database.ContactDatabase;
import in.amankumar110.phonebookapp.models.ContactsRepository;
import in.amankumar110.phonebookapp.services.ContactsService;
import in.amankumar110.phonebookapp.viewmodel.ContactViewModel;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static ContactDatabase provideContactDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, ContactDatabase.class, "contacts_db").build();
    }

    @Provides
    @Singleton
    public static ContactsService provideContactsService(ContactDatabase db) {
        return db.getContactsService();
    }

    @Provides
    @Singleton
    public static ContactsRepository provideContactsRepository(ContactsService contactsService) {
        return new ContactsRepository(contactsService);
    }


}