package org.vnuk.usermbs.data.personAPI.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PersonList {
    @SerializedName("results")
    private List<PersonEntity> persons = new ArrayList<>();

    public List<PersonEntity> getPersons() {
        return persons;
    }
}
