package com.macbitsgoa.comrades.useractivity;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserActivityDao {

    @Query("SELECT * FROM UserActivityModel ORDER BY timeStamp DESC")
    LiveData<List<UserActivityModel>> getRecentActivity();

    @Insert
    void insertRecent(UserActivityModel userActivityModel);
}
