package org.vnuk.usermbs.data.room.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeWithWarehouses {
    @Embedded
    public Employee employee;
    @Relation(
            parentColumn = "employee_id",
            entityColumn = "fk_employee_id"
    )
    public List<Warehouse> warehouseList;
}
