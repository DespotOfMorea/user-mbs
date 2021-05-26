package org.vnuk.usermbs.data.personAPI;

import org.vnuk.usermbs.data.personAPI.entity.PersonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonAPIService {
    @GET("/api/")
    Call<PersonList> getPerson();

    @GET("/api/")
    Call<PersonList> getPersons(@Query("inc") String inc,
                                @Query("results") int results);

    @GET("/api/")
    Call<PersonList> getPersons(@Query("nat") String nat,
                                        @Query("inc") String inc,
                                        @Query("results") int results);
}
