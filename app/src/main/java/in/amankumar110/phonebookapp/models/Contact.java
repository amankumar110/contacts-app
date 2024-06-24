package in.amankumar110.phonebookapp.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import in.amankumar110.phonebookapp.R;

@Entity(tableName = "contacts_table")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String homeNumber;
    @ColumnInfo
    private String phoneNumber;
    @ColumnInfo
    private String emailAddress;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String description;

    @ColumnInfo
    private String imagePath;

    public Contact(int id, String homeNumber, String phoneNumber, String emailAddress, String name, String description, String imagePath) {
        this.id = id;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public Contact() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", homeNumber='" + homeNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @BindingAdapter({"imagePath"})
    public static void loadImage(ShapeableImageView imageView,String url) {

        int index = (int) (Math.random() * 100);
        String imageUrl = "https://randomuser.me/api/portraits/men/"+index+".jpg";

            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.user)
                    .into(imageView);
        }

    }

