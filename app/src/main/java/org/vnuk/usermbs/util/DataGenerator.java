package org.vnuk.usermbs.util;

import org.vnuk.usermbs.data.room.entity.Buyer;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.data.room.entity.User;
import org.vnuk.usermbs.data.room.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class DataGenerator {

    public static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Admin", "admin", "admin11"));
        users.add(new User("User1", "user1", "user111"));
        users.add(new User("User2", "user2", "user222"));
        users.add(new User("User3", "user3", "user222"));

        return users;
    }

    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Employee", "Czech", "employee1", "Prague"));
        employees.add(new Employee("Employee", "Polish", "employee2", "Warsaw"));
        employees.add(new Employee("Employee", "Serbian", "employee3", "Belgrade"));
        employees.add(new Employee("Employee", "Slovakian", "employee4", "Bratislava"));

        return employees;
    }

    public static List<Warehouse> generateWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse("prague1", "Prague", 1));
        warehouses.add(new Warehouse("prague2", "Prague", 1));
        warehouses.add(new Warehouse("warsaw1", "Warsaw", 2));
        warehouses.add(new Warehouse("belgrade1", "Belgrade", 3));
        warehouses.add(new Warehouse("belgrade2", "Belgrade", 3));
        warehouses.add(new Warehouse("belgrade3", "Belgrade", 3));
        warehouses.add(new Warehouse("bratislava1", "Bratislava", 4));
        warehouses.add(new Warehouse("bratislava2", "Bratislava", 4));

        return warehouses;
    }

    public static List<Buyer> generateBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("buyer1", 100000010, "bc11", 1));
        buyers.add(new Buyer("buyer2", 100000020, "bc12", 1));
        buyers.add(new Buyer("buyer3", 100000030, "bc13", 1));
        buyers.add(new Buyer("buyer4", 100000040, "bc21", 2));
        buyers.add(new Buyer("buyer5", 100000050, "bc22", 2));
        buyers.add(new Buyer("buyer6", 100000060, "bc31", 3));
        buyers.add(new Buyer("buyer7", 100000070, "bc32", 3));
        buyers.add(new Buyer("buyer8", 100000080, "bc41", 4));
        buyers.add(new Buyer("buyer9", 100000090, "bc42", 4));

        return buyers;
    }
}