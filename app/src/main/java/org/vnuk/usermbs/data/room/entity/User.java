package org.vnuk.usermbs.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(tableName = "users", indices = {
        @Index(value = {"name"}, unique = true),
        @Index(value = {"user_name"}, unique = true)})
public class User {
    @Ignore
    public static final int MIN_PASSWORD_LENGTH = 6;

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private Long userID;

    private String name;
    @ColumnInfo(name = "user_name")
    private String userName;
    private String password;

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public boolean isPasswordLongEnough() {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }
}
