package com.macbitsgoa.comrades.useractivity;

import android.app.Application;
import android.os.AsyncTask;

import com.macbitsgoa.comrades.persistance.Database;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserActivityRepository {
    private UserActivityDao userActivityDao;


    UserActivityRepository(Application application) {
        Database db = Database.getInstance(application);
        userActivityDao = db.getUserActivityDao();
    }

    public LiveData<List<UserActivityModel>> getRecentActivity() {
        return userActivityDao.getRecentActivity();
    }

    public void insertActivity(UserActivityModel userActivityModel) {
        new UserActivityRepository.insertAsyncTask(userActivityDao).execute(userActivityModel);
    }


    private static class insertAsyncTask extends AsyncTask<UserActivityModel, Void, Void> {

        private UserActivityDao mAsyncTaskDao;

        insertAsyncTask(UserActivityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserActivityModel... params) {
            mAsyncTaskDao.insertRecent(params[0]);
            return null;
        }
    }
}
