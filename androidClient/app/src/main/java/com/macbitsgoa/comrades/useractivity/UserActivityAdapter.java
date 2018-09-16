package com.macbitsgoa.comrades.useractivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.comrades.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserActivityAdapter extends RecyclerView.Adapter<UserActivityViewHolder> {
    private ArrayList<UserActivityModel> userActivityModels;

    public UserActivityAdapter(ArrayList<UserActivityModel> userActivityModels) {
        this.userActivityModels = userActivityModels;
    }

    @NonNull
    @Override
    public UserActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.vh_user_activity, parent, false);
        return new UserActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserActivityViewHolder holder, int position) {
        holder.populate(userActivityModels.get(position));

    }

    @Override
    public int getItemCount() {
        return userActivityModels.size();
    }
}
