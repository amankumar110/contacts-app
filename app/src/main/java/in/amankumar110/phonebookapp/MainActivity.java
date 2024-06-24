package in.amankumar110.phonebookapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import in.amankumar110.phonebookapp.databinding.ActivityMainBinding;
import in.amankumar110.phonebookapp.models.Contact;
import in.amankumar110.phonebookapp.view.ContactAdapter;
import in.amankumar110.phonebookapp.viewmodel.ContactViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .add(R.id.fragment_container,new ContactListFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof ContactFragment || currentFragment instanceof SearchContactsFragment)
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                    .replace(R.id.fragment_container,new ContactListFragment())
                    .commit();
        else
            super.onBackPressed();
    }

}