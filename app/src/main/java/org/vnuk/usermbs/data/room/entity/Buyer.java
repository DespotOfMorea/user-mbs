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

@Entity(tableName = "buyers",
        foreignKeys = {@ForeignKey(entity = Employee.class,
                parentColumns = "employee_id",
                childColumns = "fk_employee_id",
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = {"pib"}, unique = true)})
public class Buyer {
    private static final int PIB_MIN = 100000010;
    private static final int PIB_MAX = 999999999;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "buyer_id")
    private Long buyerID;

    private String name;
    private int pib;
    private String code;

    @ColumnInfo(name = "fk_employee_id")
    private long fkEmployeeID;

    @Ignore
    private Employee employee;

    public Buyer(String name, int pib, String code, long fkEmployeeID) {
        this.name = name;
        this.pib = pib;
        this.code = code;
        this.fkEmployeeID = fkEmployeeID;
    }

    public boolean isPIBValid() {
        return (pib >= PIB_MIN && pib <= PIB_MAX);
    }
}
