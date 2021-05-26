package org.vnuk.usermbs.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.personAPI.entity.PersonEntity;
import org.vnuk.usermbs.repository.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {
    private static final String ALL_NATIONS = "All";

    private PersonRepository personRepository;
    private MutableLiveData<List<PersonEntity>> mldPersons;
    public MutableLiveData<String> nationality;
    public ObservableInt selectedNationalityPosition;
    public MutableLiveData<Integer> number;
    public ObservableInt selectedNumberPosition;
    public MutableLiveData<String> error;

    public static final String[] NATIONALITIES = new String[]{ALL_NATIONS, "AU", "CA", "DE", "ES", "FR", "GB", "IE", "NL", "NZ"};
    public static final int[] NUMBERS = new int[]{1, 5, 10, 15, 20, 25, 30};

    public PersonViewModel(@NonNull Application application) {
        super(application);
        personRepository = PersonRepository.getInstance();
        mldPersons = personRepository.getMldPersons();
        nationality = new MutableLiveData<>();
        selectedNationalityPosition = new ObservableInt();
        number = new MutableLiveData<>();
        selectedNumberPosition = new ObservableInt();
        error = personRepository.getMldAPIError();
    }

    public void onLoadApplicantsClick() {
        String natValue = NATIONALITIES[selectedNationalityPosition.get()];//nationality.getValue();
        Integer numberValue = NUMBERS[selectedNumberPosition.get()];//number.getValue();

        if (TextUtils.isEmpty(natValue)
                || numberValue == null) {
            error.setValue(getApplication().getString(R.string.error_empty_field));
            return;
        }

        if (natValue.equals(ALL_NATIONS) && numberValue.equals(1)) {
            personRepository.fetchPerson();
            return;
        }
        if (natValue.equals(ALL_NATIONS)) {
            personRepository.fetchPersons(numberValue);
            return;
        }

        personRepository.fetchPersons(natValue, numberValue);
    }

    public void onSelectNationalityItem(AdapterView<?> parent, View view, int pos, long id) {
        String nationality = (String) parent.getSelectedItem();
        this.nationality.setValue(nationality);
    }

    public void onSelectResultsItem(AdapterView<?> parent, View view, int pos, long id) {
        Integer result = (Integer) parent.getSelectedItem();
        this.number.setValue(result);
    }

    public void fetchPersons(int numberOfResults) {
        personRepository.fetchPersons(numberOfResults);
    }

    @BindingAdapter({"bind:urlInString"})
    public static void loadImage(ImageView imageView, String urlInString) {
        Log.v("Picasso", "Setting formula image in ImageView.");
        Picasso.get()
                .load(urlInString)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.v("Picasso", "Image loaded from offline cache.");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.v("Picasso", "Could not fetch image from offline cache.");
                        Picasso.get()
                                .load(urlInString)
                                .error(R.drawable.ic_baseline_hide_image_48)
                                .into(imageView, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.v("Picasso", "Image downloaded successfully.");
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Log.v("Picasso", "Could not fetch image from online source.");
                                    }
                                });
                    }
                });
    }

    public MutableLiveData<List<PersonEntity>> getMldPersons() {
        return mldPersons;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
