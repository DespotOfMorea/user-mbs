package org.vnuk.usermbs.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.data.room.WarehouseDB;
import org.vnuk.usermbs.data.room.entity.Buyer;
import org.vnuk.usermbs.util.event.Event;

public class BuyerRepository {
    private static final String TAG = BuyerRepository.class.getSimpleName();

    private static volatile BuyerRepository instance;
    private final WarehouseDB mDatabase;
    private MutableLiveData<Event<Long>> mldBuyerID;

    private BuyerRepository(@NonNull Application application) {
        mDatabase = WarehouseDB.getInstance(application);
        mldBuyerID = new MutableLiveData<>();
        Log.v(TAG, "Finished creating.");
    }

    public static synchronized BuyerRepository getInstance(@NonNull Application application) {
        if (instance == null) {
            synchronized (BuyerRepository.class) {
                if (instance == null) {
                    instance = new BuyerRepository(application);
                }
            }
        }
        return instance;
    }

    public void insert(Buyer buyer) {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            Long id = mDatabase.getBuyerDao().insert(buyer);
            mldBuyerID.postValue(new Event<>(id));
        });
    }

    public MutableLiveData<Event<Long>> getMldBuyerID() {
        return mldBuyerID;
    }
}
