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
    private boolean isNameValid = false;
    private boolean isEmailValid = false;
    private boolean isPhoneNumberValid = false;
    private boolean isHomeNumberValid = false;

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
            isNameValid = false;
        } else if (!Pattern.matches("^[A-Za-z]+(?: [A-Za-z]+)?$",name)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_NAME);
            isNameValid = false;
        } else {
            isNameValid = true;
        }
    }

    public void validatePhoneNumber(TextInputLayout textInputLayout) {

        String phone = textInputLayout.getEditText().getText().toString();

        if(phone.isEmpty() || phone==null) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isPhoneNumberValid = false;
        } else if (!Pattern.matches("^0\\d{10}$",phone)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_PHONE_NUMBER);
            isPhoneNumberValid = false;
        }else if (phone.length() < 11){
            errorSetter.setError(textInputLayout,ERROR_INVALID_PHONE_NUMBER);
            isPhoneNumberValid = false;
        } else {
            isPhoneNumberValid = true;
        }
    }

    public void validateHomeNumber(TextInputLayout textInputLayout) {

        String phone = textInputLayout.getEditText().getText().toString();

        if(phone.trim().isEmpty()) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isHomeNumberValid = false;
        } else if (!Pattern.matches("^0\\d{10}$",phone)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_HOME_NUMBER);
            isHomeNumberValid = false;
        } else if(phone.length() < 11) {
           errorSetter.setError(textInputLayout,ERROR_INVALID_HOME_NUMBER);
           isHomeNumberValid =  false;
        } else {
            isHomeNumberValid = true;
        }
    }

    public void validateEmailAddress(TextInputLayout textInputLayout) {

        String email = textInputLayout.getEditText().getText().toString();

        if(email.trim().isEmpty()) {
            errorSetter.setError(textInputLayout,ERROR_EMPTY_FIELD);
            isEmailValid = false;
        } else if (!Pattern.matches( "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$",email)) {
            errorSetter.setError(textInputLayout,ERROR_INVALID_EMAIL);
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }
    }


    public void resetValidationFlags() {
        isNameValid = false;
        isEmailValid = false;
        isPhoneNumberValid = false;
        isHomeNumberValid = false;
    }

    public boolean isAllValid() {
        return isNameValid && isEmailValid && isPhoneNumberValid && isHomeNumberValid;
    }

}
