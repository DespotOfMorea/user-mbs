package org.vnuk.usermbs.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.vnuk.usermbs.data.room.entity.Warehouse;

import java.util.List;

@Dao
public interface WarehouseDao {
    @Query("SELECT * FROM warehouses")
    List<Warehouse> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Warehouse warehouse);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long[] insertAll(List<Warehouse> warehouses);

    @Update
    void update(Warehouse warehouse);

    @Delete
    void delete(Warehouse warehouse);

    @Delete
    void delete(Warehouse... warehouses);
}
