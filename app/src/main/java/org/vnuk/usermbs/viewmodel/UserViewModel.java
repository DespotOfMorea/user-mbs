package org.vnuk.usermbs.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.User;
import org.vnuk.usermbs.repository.UserRepository;

import static org.vnuk.usermbs.data.room.entity.User.MIN_PASSWORD_LENGTH;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<Boolean> isValidLogin;
    private MutableLiveData<Long> mldUserID;
    public MutableLiveData<String> name;
    public MutableLiveData<String> username;
    public MutableLiveData<String> password;
    public MutableLiveData<String> rePassword;
    public MutableLiveData<String> error;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        isValidLogin = userRepository.getIsValidLogin();
        mldUserID = userRepository.getMldUserID();
        name = new MutableLiveData<>();
        username = new MutableLiveData<>();
        password = new MutableLiveData<>();
        rePassword = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public void onLogInClick() {
        String usernameValue = username.getValue();
        String passValue = password.getValue();
        if (TextUtils.isEmpty(usernameValue)
                || TextUtils.isEmpty(passValue)) {
            error.setValue(getApplication().getString(R.string.error_empty_field));
            return;
        }

        userRepository.checkLogin(usernameValue, passValue);
    }

    public void clearLoginData() {
        username.setValue("");
        password.setValue("");
    }

    public void onNewUserClick() {
        String nameValue = name.getValue();
        String usernameValue = username.getValue();
        String passValue = password.getValue();
        String rePassValue = rePassword.getValue();
        if (TextUtils.isEmpty(nameValue)
                || TextUtils.isEmpty(usernameValue)
                || TextUtils.isEmpty(passValue)
                || TextUtils.isEmpty(rePassValue)) {
            error.setValue(getApplication().getString(R.string.error_empty_field));
            return;
        }

        if (isPasswordValid(passValue, rePassValue)) {
            User user = new User(nameValue, usernameValue, passValue);
            insertUser(user);
        }
    }

    private boolean isPasswordValid(String passValue, String rePassValue) {
        int passLength = MIN_PASSWORD_LENGTH;

        if (passValue.length() < passLength) {
            error.setValue(getApplication().getString(R.string.error_password, passLength));
            return false;
        } else {
            if (!passValue.equals(rePassValue)) {
                error.setValue(getApplication().getString(R.string.error_reenter_password));
                return false;
            }
        }
        return true;
    }

    public void insertUser(User user) {
        userRepository.insert(user);
    }

    public MutableLiveData<Boolean> getIsValidLogin() {
        return isValidLogin;
    }

    public MutableLiveData<Long> getMldUserID() {
        return mldUserID;
    }
}
