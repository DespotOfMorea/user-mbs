package org.vnuk.usermbs.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.data.room.WarehouseDB;
import org.vnuk.usermbs.data.room.entity.Warehouse;
import org.vnuk.usermbs.util.event.Event;

public class WarehouseRepository {
    private static final String TAG = WarehouseRepository.class.getSimpleName();

    private static WarehouseRepository instance;
    private final WarehouseDB mDatabase;
    private MutableLiveData<Event<Long>> mldWarehouseID;

    private WarehouseRepository(@NonNull Application application) {
        mDatabase = WarehouseDB.getInstance(application);
        mldWarehouseID = new MutableLiveData<>();
        Log.v(TAG, "Finished creating.");
    }

    public static synchronized WarehouseRepository getInstance(@NonNull Application application) {
        if (instance == null) {
            synchronized (WarehouseRepository.class) {
                if (instance == null) {
                    instance = new WarehouseRepository(application);
                }
            }
        }
        return instance;
    }

    public void insert(Warehouse warehouse) {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            Long id = mDatabase.getWarehouseDao().insert(warehouse);

            mldWarehouseID.postValue(new Event<>(id));
        });
    }

    public MutableLiveData<Event<Long>> getMldWarehouseID() {
        return mldWarehouseID;
    }
}
