package in.amankumar110.phonebookapp.eventListeners;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import in.amankumar110.phonebookapp.ContactFragment;
import in.amankumar110.phonebookapp.R;
import in.amankumar110.phonebookapp.models.Contact;

public class ContactItemEventListeners {

    FragmentManager fragmentManager;
    Contact contact;
    public ContactItemEventListeners(FragmentManager fragmentManager, Contact contact) {
        this.fragmentManager = fragmentManager;
        this.contact = contact;
    }

    public void onContactItemClicked(View v) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .replace(R.id.fragment_container,new ContactFragment(contact))
                .commit();
    }
}
