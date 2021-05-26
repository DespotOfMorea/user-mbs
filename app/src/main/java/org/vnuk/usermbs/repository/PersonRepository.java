package org.vnuk.usermbs.repository;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.vnuk.usermbs.data.personAPI.PersonAPIClient;
import org.vnuk.usermbs.data.personAPI.PersonAPIService;
import org.vnuk.usermbs.data.personAPI.entity.PersonEntity;
import org.vnuk.usermbs.data.personAPI.entity.PersonList;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PersonRepository {
    private static final String TAG = PersonRepository.class.getSimpleName();
    private static final String DEF_INC = "name,location,picture,nat";

    private static volatile PersonRepository instance;

    private PersonAPIService apiService;
    private MutableLiveData<List<PersonEntity>> mldPersons;
    private MutableLiveData<String> mldAPIError;

    private PersonRepository() {
        apiService = PersonAPIClient.getClient().create(PersonAPIService.class);
        mldPersons = new MutableLiveData<>();
        mldAPIError = new MutableLiveData<>();
        Log.v(TAG, "Finished creating.");
    }

    public static synchronized PersonRepository getInstance() {
        if (instance == null) {
            synchronized (PersonRepository.class) {
                if (instance == null) {
                    instance = new PersonRepository();
                }
            }
        }
        return instance;
    }

    public void fetchPerson() {
        Log.v(TAG, "Fetching person.");
        Call<PersonList> call = apiService.getPerson();

        call.enqueue(new Callback<PersonList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PersonList> call, Response<PersonList> response) {
                callOnResponse(response);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PersonList> call, Throwable t) {
                callOnFailure(call);
            }
        });
    }

    public void fetchPersons(int numberOfResults) {
        Log.v(TAG, "Fetching persons.");
        Call<PersonList> call = apiService.getPersons(DEF_INC, numberOfResults);

        call.enqueue(new Callback<PersonList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PersonList> call, Response<PersonList> response) {
                callOnResponse(response);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PersonList> call, Throwable t) {
                callOnFailure(call);
            }
        });
    }


    public void fetchPersons(String nat, int numberOfResults) {
        Log.v(TAG, "Fetching persons of specific nationality.");
        Call<PersonList> call = apiService.getPersons(nat, DEF_INC, numberOfResults);

        call.enqueue(new Callback<PersonList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PersonList> call, Response<PersonList> response) {
                callOnResponse(response);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PersonList> call, Throwable t) {
                callOnFailure(call);
            }
        });
    }

    private void callOnResponse(Response<PersonList> response) {
        if (response.isSuccessful()) {
            Log.i(TAG, "GET Persons: call was successful.");
            mldPersons.setValue(response.body().getPersons());
        } else {
            Log.e(TAG, "GET Persons: There was some kind of error.");
            try {
                JsonObject jsonObject = new Gson().fromJson(response.errorBody().string(), JsonObject.class);
                String error = jsonObject.get("error").toString();
                if (!TextUtils.isEmpty(error))
                    mldAPIError.setValue(error);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
                mldPersons.setValue(null);
            }
        }
    }

    private void callOnFailure(Call<PersonList> call) {
        Log.e(TAG, "GET Persons: call failed.");
        mldPersons.setValue(null);
        call.cancel();
    }

    public MutableLiveData<List<PersonEntity>> getMldPersons() {
        return mldPersons;
    }

    public MutableLiveData<String> getMldAPIError() {
        return mldAPIError;
    }
}
