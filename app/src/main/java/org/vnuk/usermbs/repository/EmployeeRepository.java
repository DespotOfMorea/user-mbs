package org.vnuk.usermbs.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.data.room.WarehouseDB;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.data.room.entity.EmployeeWithBuyers;
import org.vnuk.usermbs.util.event.Event;

import java.util.List;

public class EmployeeRepository {
    private static final String TAG = EmployeeRepository.class.getSimpleName();

    private static volatile EmployeeRepository instance;
    private final WarehouseDB mDatabase;
    private MutableLiveData<Event<Long>> mldEmployeeID;
    private MutableLiveData<List<Employee>> mldEmployees;
    private MutableLiveData<List<EmployeeWithBuyers>> mldEmployeeWithBuyers;

    private EmployeeRepository(@NonNull Application application) {
        mDatabase = WarehouseDB.getInstance(application);
        mldEmployeeID = new MutableLiveData<>();
        mldEmployees = new MutableLiveData<>();
        mldEmployeeWithBuyers = new MutableLiveData<>();
        Log.v(TAG, "Finished creating.");
    }

    public static synchronized EmployeeRepository getInstance(@NonNull Application application) {
        if (instance == null) {
            synchronized (EmployeeRepository.class) {
                if (instance == null) {
                    instance = new EmployeeRepository(application);
                }
            }
        }
        return instance;
    }

    public void insert(Employee employee) {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            Long id = mDatabase.getEmployeeDao().insert(employee);
            mldEmployeeID.postValue(new Event<>(id));
        });
    }

    public void fetchEmployees() {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            List<Employee> employees = mDatabase.getEmployeeDao().getAll();
            this.mldEmployees.postValue(employees);
        });
    }

    public void fetchBuyers(Long employeeID) {
        WarehouseDB.databaseWriteExecutor.execute(() -> {
            List<EmployeeWithBuyers> buyers = mDatabase.getEmployeeDao().getEmployeeWithBuyers(employeeID);
            this.mldEmployeeWithBuyers.postValue(buyers);
        });
    }

    public MutableLiveData<Event<Long>> getMldEmployeeID() {
        return mldEmployeeID;
    }

    public MutableLiveData<List<Employee>> getMldEmployees() {
        return mldEmployees;
    }

    public MutableLiveData<List<EmployeeWithBuyers>> getMldEmployeeWithBuyers() {
        return mldEmployeeWithBuyers;
    }
}
