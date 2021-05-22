package org.vnuk.usermbs.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.data.room.WarehouseDB;
import org.vnuk.usermbs.data.room.entity.User;
import org.vnuk.usermbs.util.event.Event;

public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();

    private static volatile UserRepository instance;
    private final WarehouseDB mDatabase;
    private MutableLiveData<Event<Long>> mldUserID;
    private MutableLiveData<Boolean> isValidLogin;

    private UserRepository(@NonNull Application application) {
        mDatabase = WarehouseDB.getInstance(application);
        mldUserID = new MutableLiveData<>();
        isValidLogin = new MutableLiveData<>();
        Log.v(TAG, "Finished creating.");
    }

    public static synchronized UserRepository getInstance(@NonNull Application application) {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository(application);
                }
            }
        }
        return instance;
    }

    public void insert(User user) {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            Long id = mDatabase.getUserDao().insert(user);
            mldUserID.postValue(new Event<>(id));
        });
    }

    public void checkLogin(String userName, String password) {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            boolean login = mDatabase.getUserDao().isUserLoginValid(userName, password);
            this.isValidLogin.postValue(login);
        });
    }

    public MutableLiveData<Event<Long>> getMldUserID() {
        return mldUserID;
    }

    public MutableLiveData<Boolean> getIsValidLogin() {
        return isValidLogin;
    }
}
