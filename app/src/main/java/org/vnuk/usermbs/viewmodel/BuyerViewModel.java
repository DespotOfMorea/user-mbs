package org.vnuk.usermbs.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.Buyer;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.repository.BuyerRepository;
import org.vnuk.usermbs.util.event.Event;

import java.util.List;

public class BuyerViewModel extends AndroidViewModel {
    private BuyerRepository buyerRepository;
    private MutableLiveData<Event<Long>> mldBuyerID;
    public MutableLiveData<String> name;
    public MutableLiveData<Integer> pib;
    public MutableLiveData<String> code;
    public MutableLiveData<Long> employeeID;
    public MutableLiveData<String> error;
    public MutableLiveData<List<Employee>> employees;

    public BuyerViewModel(@NonNull Application application) {
        super(application);
        buyerRepository = BuyerRepository.getInstance(application);
        mldBuyerID = buyerRepository.getMldBuyerID();
        name = new MutableLiveData<>();
        pib = new MutableLiveData<>();
        code = new MutableLiveData<>();
        employeeID = new MutableLiveData<>();
        error = new MutableLiveData<>();
        employees = new MutableLiveData<>();
    }

    public void onNewBuyerClick() {
        String nameValue = name.getValue();
        Integer pibValue = pib.getValue();
        String codeValue = code.getValue();
        Long employeeIDValue = employeeID.getValue();

        if (TextUtils.isEmpty(nameValue)
                || pibValue == null
                || TextUtils.isEmpty(codeValue)
                || employeeIDValue == null) {
            error.setValue(getApplication().getString(R.string.error_empty_field));
            return;
        }

        Buyer buyer = new Buyer(nameValue, pibValue, codeValue, employeeIDValue);
        if (!buyer.isPIBValid()) {
            error.setValue(getApplication().getString(R.string.error_pib));
            return;
        }

        buyerRepository.insert(buyer);
    }

    public MutableLiveData<Event<Long>> getMldBuyerID() {
        return mldBuyerID;
    }
}
