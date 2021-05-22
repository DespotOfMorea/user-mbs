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
        users.add(new User("User", "user", "user111"));
        users.add(new User("Edward Green", "greedw", "edwgre"));
        users.add(new User("Patrick Wood", "woopat", "patwoo"));
        users.add(new User("Falk Reppe", "repfal", "falrep"));
        users.add(new User("Kyle Torres", "torkyl", "kyltor"));
        users.add(new User("Ellen Ward", "warell", "ellwar"));
        users.add(new User("Sevde Wensveen", "wensev", "sevwen"));

        return users;
    }

    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Peggy", "Stone", "emplstopeg", "Palm Bay"));
        employees.add(new Employee("Nixon", "Jones", "empljonnix", "Christchurch"));
        employees.add(new Employee("Michael", "Raddatz", "emplradmic", "Tecklenburg"));
        employees.add(new Employee("Nolan", "Cox", "emplcoxnol", "Birr"));
        employees.add(new Employee("Isaac", "Laurent", "empllauisa", "Vitry-sur-Seine"));
        employees.add(new Employee("Sofia", "Madsen", "emplmadsof", "Fredeikssund"));
        employees.add(new Employee("Micheal", "Barnes", "emplbarmic", "Gloucester"));
        employees.add(new Employee("Sean", "Peters", "emplpetsea", "Sydney"));
        employees.add(new Employee("Nathaniel", "Thomas", "emplthonat", "Hamilton"));
        employees.add(new Employee("Margie", "Watkins", "emplwatmar", "Round Rock"));

        return employees;
    }

    public static List<Warehouse> generateWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse("Palm Bay WH1", "Palm Bay", 1));
        warehouses.add(new Warehouse("Palm Bay WH2", "Palm Bay", 1));
        warehouses.add(new Warehouse("Christchurch WH1", "Christchurch", 2));
        warehouses.add(new Warehouse("Tecklenburg WH1", "Tecklenburg", 3));
        warehouses.add(new Warehouse("Tecklenburg WH2", "Tecklenburg", 3));
        warehouses.add(new Warehouse("Tecklenburg WH3", "Tecklenburg", 3));
        warehouses.add(new Warehouse("Birr WH1", "Birr", 4));
        warehouses.add(new Warehouse("Birr WH2", "Birr", 4));
        warehouses.add(new Warehouse("Vitry-sur-Seine WH1", "Vitry-sur-Seine", 5));
        warehouses.add(new Warehouse("Vitry-sur-Seine WH2", "Vitry-sur-Seine", 5));
        warehouses.add(new Warehouse("Fredeikssund WH1", "Fredeikssund", 6));
        warehouses.add(new Warehouse("Gloucester WH1", "Gloucester", 7));
        warehouses.add(new Warehouse("Gloucester WH2", "Gloucester", 7));
        warehouses.add(new Warehouse("Gloucester WH3", "Gloucester", 7));
        warehouses.add(new Warehouse("Sydney WH1", "Sydney", 8));
        warehouses.add(new Warehouse("Sydney WH2", "Sydney", 8));
        warehouses.add(new Warehouse("Hamilton WH1", "Hamilton", 9));
        warehouses.add(new Warehouse("Hamilton WH2", "Hamilton", 9));
        warehouses.add(new Warehouse("Hamilton WH3", "Hamilton", 9));
        warehouses.add(new Warehouse("Round Rock WH1", "Round Rock", 10));
        warehouses.add(new Warehouse("Round Rock WH2", "Round Rock", 10));

        return warehouses;
    }

    public static List<Buyer> generateBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("Piotr Vollen", 100000010, "buyvolpio", 1));
        buyers.add(new Buyer("Alex Bryant", 100000020, "buybryale", 1));
        buyers.add(new Buyer("Ricky Fletcher", 100000030, "buyfleric", 1));
        buyers.add(new Buyer("Anna Christiansen", 100000040, "buychrann", 2));
        buyers.add(new Buyer("Torsten Dreyer", 100000050, "buydretor", 2));
        buyers.add(new Buyer("William Møller", 100000060, "buymolwil", 3));
        buyers.add(new Buyer("Sara Christensen", 100000070, "buychrsar", 3));
        buyers.add(new Buyer("Justin Fortin", 100000080, "buyforjus", 4));
        buyers.add(new Buyer("Eliza Harris", 100000090, "buyhareli", 4));
        buyers.add(new Buyer("Joel Hamalainen", 100000110, "buyhamjoe", 5));
        buyers.add(new Buyer("Alexis Singh", 100000120, "buysinale", 5));
        buyers.add(new Buyer("Angelina Weterings", 100000130, "buywetang", 5));
        buyers.add(new Buyer("Nick Roy", 100000140, "buyroynic", 6));
        buyers.add(new Buyer("Freddie Willis", 100000150, "buywilfre", 6));
        buyers.add(new Buyer("Lily Richards", 100000160, "buyriclil", 7));
        buyers.add(new Buyer("Albert Madsen", 100000170, "buymadalb", 7));
        buyers.add(new Buyer("Rose Meyer", 100000180, "buymeyros", 8));
        buyers.add(new Buyer("Albert Vestly", 100000190, "buyvesalb", 8));
        buyers.add(new Buyer("Sandy Storch", 100000210, "buystosan", 5));
        buyers.add(new Buyer("Angel Jenkins", 100000220, "buyjenang", 5));
        buyers.add(new Buyer("Brian Bell", 100000230, "buybelbri", 5));
        buyers.add(new Buyer("Ida Shelton", 100000240, "buysheida", 6));
        buyers.add(new Buyer("Lilly Picard", 100000250, "buypivlil", 6));


        return buyers;
    }
}