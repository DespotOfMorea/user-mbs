package org.vnuk.usermbs.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.personAPI.entity.PersonEntity;
import org.vnuk.usermbs.repository.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private MutableLiveData<List<PersonEntity>> mldPersons;
    public MutableLiveData<String> error;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        personRepository = PersonRepository.getInstance();
        mldPersons = personRepository.getMldPersons();
        error = personRepository.getMldAPIError();
    }

    public void onLoadApplicantsClick() {
        personRepository.fetchPersons(30);
    }

    public void fetchPersons(int number) {
        personRepository.fetchPersons(number);
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
