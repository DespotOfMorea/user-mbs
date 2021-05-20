package org.vnuk.usermbs.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(tableName = "employees",
        indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
public class Employee {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employee_id")
    private Long employeeID;

    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    private String code;
    private String city;

    @Ignore
    private List<Warehouse> warehouses;
    @Ignore
    private List<Buyer> buyers;

    public Employee(String firstName, String lastName, String code, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.city = city;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
