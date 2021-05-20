package org.vnuk.usermbs.data.room.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmployeeWithBuyers {
    @Embedded
    public Employee employee;
    @Relation(
            parentColumn = "employee_id",
            entityColumn = "fk_employee_id"
    )
    public List<Buyer> buyersList;
}
