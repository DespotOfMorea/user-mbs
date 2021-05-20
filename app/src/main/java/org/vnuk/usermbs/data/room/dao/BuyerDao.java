package org.vnuk.usermbs.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.vnuk.usermbs.data.room.entity.Buyer;

import java.util.List;

@Dao
public interface BuyerDao {
    @Query("SELECT * FROM buyers")
    List<Buyer> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Buyer buyer);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long[] insertAll(List<Buyer> buyers);

    @Update
    void update(Buyer buyer);

    @Delete
    void delete(Buyer buyer);

    @Delete
    void delete(Buyer... buyers);
}
