package org.vnuk.usermbs.data.personAPI.entity;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonEntity {
    @SerializedName("name")
    private Name name;

    @SerializedName("location")
    private Location location;

    @SerializedName("picture")
    private Picture picture;

    @SerializedName("nat")
    private String nationality;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class Name {
        @SerializedName("first")

        private String firstName;
        @SerializedName("last")

        private String lastName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class Location {
        @SerializedName("city")
        private String city;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class Picture {
        @SerializedName("large")
        private String personImage;
    }

    public String getFirstName() {
        return name.firstName;
    }

    public String getLastName() {
        return name.lastName;
    }

    public String getFullName() {
        return name.firstName + " " + name.lastName;
    }

    public String getCity() {
        return location.city;
    }

    public String getThumbURL() {
        return picture.personImage;
    }
}
