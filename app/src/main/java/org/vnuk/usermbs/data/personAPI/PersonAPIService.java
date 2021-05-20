package org.vnuk.usermbs.data.personAPI;

import org.vnuk.usermbs.data.personAPI.entity.PersonEntity;
import org.vnuk.usermbs.data.personAPI.entity.PersonList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonAPIService {
    @GET("/api/")
    Call<List<PersonEntity>> getPerson();

    @GET("/api/")
    Call<PersonList> getPersons(@Query("inc") String inc,
                                @Query("results") int results);

    @GET("/api/")
    Call<List<PersonEntity>> getPersons(@Query("nat") String nat,
                                        @Query("inc") String inc,
                                        @Query("results") int results);
}
