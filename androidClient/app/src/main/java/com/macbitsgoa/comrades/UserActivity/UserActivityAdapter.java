package com.macbitsgoa.comrades.UserActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.comrades.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserActivityAdapter extends RecyclerView.Adapter<UserActivityViewHolder> {
    private ArrayList<UserActivityModel> userActivityModels;

    public UserActivityAdapter() {

    }

    @NonNull
    @Override
    public UserActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.vh_rank
                , parent, false);
        return new UserActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserActivityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
