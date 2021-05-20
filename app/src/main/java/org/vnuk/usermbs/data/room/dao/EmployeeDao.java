package org.vnuk.usermbs.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.data.room.entity.EmployeeWithBuyers;
import org.vnuk.usermbs.data.room.entity.EmployeeWithWarehouses;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employees")
    List<Employee> getAll();

    @Transaction
    @Query("SELECT * FROM employees WHERE employee_id =:employeeID")
    public List<EmployeeWithWarehouses> getEmployeeWithWarehouses(Long employeeID);

    @Transaction
    @Query("SELECT * FROM employees WHERE employee_id =:employeeID")
    public List<EmployeeWithBuyers> getEmployeeWithBuyers(Long employeeID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Employee employee);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long[] insertAll(List<Employee> employees);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Delete
    void delete(Employee... employees);
}
