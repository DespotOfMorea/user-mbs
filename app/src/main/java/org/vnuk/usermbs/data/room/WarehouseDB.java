package org.vnuk.usermbs.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.vnuk.usermbs.data.room.dao.BuyerDao;
import org.vnuk.usermbs.data.room.dao.EmployeeDao;
import org.vnuk.usermbs.data.room.dao.UserDao;
import org.vnuk.usermbs.data.room.dao.WarehouseDao;
import org.vnuk.usermbs.data.room.entity.Buyer;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.data.room.entity.User;
import org.vnuk.usermbs.data.room.entity.Warehouse;
import org.vnuk.usermbs.util.DataGenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Employee.class, Warehouse.class, Buyer.class}, version = 1, exportSchema = false)
public abstract class WarehouseDB extends RoomDatabase {
    public static final Long BAD_INSERT = -1L;
    private static final String DB_NAME = "warehouse_db";
    private static final int NUMBER_OF_THREADS = 4;
    private static volatile WarehouseDB instance;

    public abstract UserDao getUserDao();

    public abstract EmployeeDao getEmployeeDao();

    public abstract WarehouseDao getWarehouseDao();

    public abstract BuyerDao getBuyerDao();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WarehouseDB getInstance(Context context) {
        if (null == instance) {
            synchronized (WarehouseDB.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), WarehouseDB.class, DB_NAME)
                            .addCallback(generateDataCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback generateDataCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                List<User> users = DataGenerator.generateUsers();
                List<Employee> employees = DataGenerator.generateEmployees();
                List<Warehouse> warehouses = DataGenerator.generateWarehouses();
                List<Buyer> buyers = DataGenerator.generateBuyers();
                instance.getUserDao().insertAll(users);
                instance.getEmployeeDao().insertAll(employees);
                instance.getWarehouseDao().insertAll(warehouses);
                instance.getBuyerDao().insertAll(buyers);
            });
        }
    };
}