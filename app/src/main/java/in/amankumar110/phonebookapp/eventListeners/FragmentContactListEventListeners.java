package in.amankumar110.phonebookapp.eventListeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import in.amankumar110.phonebookapp.AddContactActivity;

public class FragmentContactListEventListeners {

    private Context context;
    public FragmentContactListEventListeners(Context context) {
        this.context =context;
    }

    public void onAddContactButtonClicked(View v) {

        context.startActivity(new Intent(context, AddContactActivity.class));
    }
}
