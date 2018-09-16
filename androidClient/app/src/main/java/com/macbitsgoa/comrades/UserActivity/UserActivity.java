package com.macbitsgoa.comrades.UserActivity;

import android.app.Activity;
import android.os.Bundle;

import com.macbitsgoa.comrades.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class UserActivity extends Activity {

    private UserActivityAdapter userActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activity);
        RecyclerView rvUserActivity = findViewById(R.id.rv_user_activity);
        userActivityAdapter = new UserActivityAdapter();
        rvUserActivity.setLayoutManager(new LinearLayoutManager(this));
        rvUserActivity.setAdapter(userActivityAdapter);
    }

}
