package org.vnuk.usermbs.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.data.room.entity.Warehouse;
import org.vnuk.usermbs.repository.WarehouseRepository;
import org.vnuk.usermbs.util.event.Event;

import java.util.List;

public class WarehouseViewModel extends AndroidViewModel {
    private WarehouseRepository warehouseRepository;
    private MutableLiveData<Event<Long>> mldWarehouseID;
    public MutableLiveData<String> name;
    public MutableLiveData<String> city;
    public MutableLiveData<Long> employeeID;
    public MutableLiveData<String> error;
    public MutableLiveData<List<Employee>> employees;

    public WarehouseViewModel(@NonNull Application application) {
        super(application);
        warehouseRepository = WarehouseRepository.getInstance(application);
        mldWarehouseID = warehouseRepository.getMldWarehouseID();
        name = new MutableLiveData<>();
        city = new MutableLiveData<>();
        employeeID = new MutableLiveData<>();
        error = new MutableLiveData<>();
        employees = new MutableLiveData<>();
    }

    public void onNewWarehouseClick() {
        String nameValue = name.getValue();
        String cityValue = city.getValue();
        Long employeeIDValue = employeeID.getValue();

        if (TextUtils.isEmpty(nameValue)
                || TextUtils.isEmpty(cityValue)
                || employeeIDValue == null) {
            error.setValue(getApplication().getString(R.string.error_empty_field));
            return;
        }

        Warehouse warehouse = new Warehouse(nameValue, cityValue, employeeIDValue);
        warehouseRepository.insert(warehouse);
    }

    public void insert(Warehouse warehouse) {
        warehouseRepository.insert(warehouse);
    }

    public MutableLiveData<Event<Long>> getMldWarehouseID() {
        return mldWarehouseID;
    }
}
