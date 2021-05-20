package org.vnuk.usermbs.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(tableName = "warehouses", foreignKeys = {@ForeignKey(entity = Employee.class,
        parentColumns = "employee_id",
        childColumns = "fk_employee_id",
        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = {"name"}, unique = true)})
public class Warehouse {
    @PrimaryKey
    @ColumnInfo(name = "warehouse_id")
    private Long warehouseID;

    private String name;
    private String city;

    @ColumnInfo(name = "fk_employee_id")
    private long fkEmployeeID;

    @Ignore
    private Employee employee;

    public Warehouse(String name, String city, long fkEmployeeID) {
        this.name = name;
        this.city = city;
        this.fkEmployeeID = fkEmployeeID;
    }
}
