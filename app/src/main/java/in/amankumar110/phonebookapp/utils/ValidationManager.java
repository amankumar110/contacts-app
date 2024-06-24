package in.amankumar110.phonebookapp.utils;

import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ValidationManager {

    public static final String ERROR_EMPTY_FIELD = "This field is required";
    public static final String ERROR_INVALID_NAME = "Enter a Valid Name!";
    public static final String ERROR_INVALID_EMAIL = "Enter a Valid Email!";
    public static final String ERROR_INVALID_PHONE_NUMBER = "Enter a Valid Phone Number!";
    public static final String ERROR_INVALID_HOME_NUMBER = "Enter a Valid Home Number!";
    public static boolean isAllValid = true;

    private ErrorSetter errorSetter;

    public interface ErrorSetter {
        void setError(TextInputLayout textInputLayout,String message);
    }

    public ValidationManager(ErrorSetter errorSetter) {
        this.errorSetter = errorSetter;
    }


    public void validateName(TextInputLayout textInputLayout) {

        String name = textInputLayout.getEditText().getText().toString();

        if(name.isEmpty() || name==null) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isAllValid = false;
        } else if (!Pattern.matches("^[A-Za-z]+(?: [A-Za-z]+)?$",name)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_NAME);
            isAllValid = false;
        } else {
            isAllValid = true;
        }
    }

    public void validatePhoneNumber(TextInputLayout textInputLayout) {

        String phone = textInputLayout.getEditText().getText().toString();

        if(phone.isEmpty() || phone==null) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isAllValid = false;
        } else if (!Pattern.matches("^0\\d{10}$",phone)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_PHONE_NUMBER);
            isAllValid = false;
        }else if (phone.length() < 11){
            errorSetter.setError(textInputLayout,ERROR_INVALID_PHONE_NUMBER);
            isAllValid = false;
        } else {
            isAllValid = true;
        }
    }

    public void validateHomeNumber(TextInputLayout textInputLayout) {

        String phone = textInputLayout.getEditText().getText().toString();

        if(phone.isEmpty() || phone==null) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isAllValid = false;
        } else if (!Pattern.matches("^0\\d{10}$",phone)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_HOME_NUMBER);
            isAllValid = false;
        } else if(phone.length() < 11) {
           errorSetter.setError(textInputLayout,ERROR_INVALID_HOME_NUMBER);
           isAllValid = false;
        } else {
            isAllValid = true;
        }
    }

    public void validateEmailAddress(TextInputLayout textInputLayout) {

        String email = textInputLayout.getEditText().getText().toString();

        if(email.isEmpty() || email==null) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isAllValid = false;
        } else if (!Pattern.matches( "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$",email)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_EMAIL);
            isAllValid = false;
        } else {
            isAllValid = true;
        }
    }

    public boolean isAllValid() {
        return isAllValid;
    }

}
