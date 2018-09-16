package com.macbitsgoa.comrades.useractivity;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserActivityVm extends AndroidViewModel {
    private UserActivityRepository userActivityRepository;

    public UserActivityVm(Application application) {
        super(application);
        userActivityRepository = new UserActivityRepository(application);
    }

    public LiveData<List<UserActivityModel>> getRecents() {
        return userActivityRepository.getRecentActivity();
    }

    public void insertRecent(UserActivityModel userActivityModel) {
        userActivityRepository.insertActivity(userActivityModel);
    }

}
