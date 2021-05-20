package org.vnuk.usermbs.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.vnuk.usermbs.data.room.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE user_name =:userName AND password =:password)")
    boolean isUserLoginValid(String userName, String password);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(User user);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long[] insertAll(List<User> users);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Delete
    void delete(User... users);
}
