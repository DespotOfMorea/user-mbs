package org.vnuk.usermbs.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.data.room.WarehouseDB;
import org.vnuk.usermbs.data.room.entity.Buyer;

public class BuyerRepository {
    private static final String TAG = BuyerRepository.class.getSimpleName();

    private static BuyerRepository instance;
    private final WarehouseDB mDatabase;
    private MutableLiveData<Long> mldBuyerID;

    private BuyerRepository(@NonNull Application application) {
        mDatabase = WarehouseDB.getInstance(application);
        mldBuyerID = new MutableLiveData<>();
        Log.v(TAG, "Finished creating.");
    }

    public static BuyerRepository getInstance(@NonNull Application application) {
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
            mldBuyerID.postValue(id);
        });
    }

    public MutableLiveData<Long> getMldBuyerID() {
        return mldBuyerID;
    }
}
