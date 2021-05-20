package org.vnuk.usermbs.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.data.room.entity.EmployeeWithBuyers;
import org.vnuk.usermbs.repository.EmployeeRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;
    private MutableLiveData<Long> mldEmployeeID;
    private MutableLiveData<List<Employee>> mldEmployees;
    private MutableLiveData<List<EmployeeWithBuyers>> mldEmployeeWithBuyers;
    public MutableLiveData<String> firstName;
    public MutableLiveData<String> lastName;
    public MutableLiveData<String> code;
    public MutableLiveData<String> city;
    public MutableLiveData<String> error;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = EmployeeRepository.getInstance(application);
        mldEmployeeID = employeeRepository.getMldEmployeeID();
        mldEmployees = employeeRepository.getMldEmployees();
        mldEmployeeWithBuyers = employeeRepository.getMldEmployeeWithBuyers();
        firstName = new MutableLiveData<>();
        lastName = new MutableLiveData<>();
        code = new MutableLiveData<>();
        city = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public void onNewEmployeeClick() {
        error.setValue("");
        String firstNameValue = firstName.getValue();
        String lastNameValue = lastName.getValue();
        String codeValue = code.getValue();
        String cityValue = city.getValue();
        if (TextUtils.isEmpty(firstNameValue)
                || TextUtils.isEmpty(lastNameValue)
                || TextUtils.isEmpty(codeValue)
                || TextUtils.isEmpty(cityValue)) {
            error.setValue(getApplication().getString(R.string.error_empty_field));
            return;
        }

        Employee employee = new Employee(firstNameValue, lastNameValue, codeValue, cityValue);
        employeeRepository.insert(employee);
    }

    public void fetchEmployees() {
        employeeRepository.fetchEmployees();
    }

    public void fetchBuyers(Long employeeID) {
        employeeRepository.fetchBuyers(employeeID);
    }

    public MutableLiveData<Long> getMldEmployeeID() {
        return mldEmployeeID;
    }

    public MutableLiveData<List<Employee>> getMldEmployees() {
        return mldEmployees;
    }

    public MutableLiveData<List<EmployeeWithBuyers>> getMldEmployeeWithBuyers() {
        return mldEmployeeWithBuyers;
    }
}
