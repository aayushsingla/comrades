package com.macbitsgoa.comrades.useractivity;

import android.os.Bundle;

import com.macbitsgoa.comrades.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class UserActivity extends AppCompatActivity {

    public static final String ACTION_COURSE_ADDED = "courseAdded";
    public static final String ACTION_FILE_ADDED = "fileAdded";
    public static final String ACTION_COURSE_RENAMED = "courseRenamed";
    public static final String ACTION_FILE_RENAMED = "fileRenamed";
    private final ArrayList<UserActivityModel> activityModels = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activity);

        Toolbar toolbar = findViewById(R.id.toolbar_user_activity);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        RecyclerView rvUserActivity = findViewById(R.id.rv_user_activity);
        UserActivityAdapter userActivityAdapter = new UserActivityAdapter(activityModels);
        rvUserActivity.setLayoutManager(new LinearLayoutManager(this));
        rvUserActivity.setAdapter(userActivityAdapter);

        UserActivityVm userActivityVm = ViewModelProviders.of(this).get(UserActivityVm.class);
        userActivityVm.getRecents().observe(this, userActivityModels -> {
            activityModels.clear();
            activityModels.addAll(userActivityModels);
            userActivityAdapter.notifyDataSetChanged();
        });

    }


}
