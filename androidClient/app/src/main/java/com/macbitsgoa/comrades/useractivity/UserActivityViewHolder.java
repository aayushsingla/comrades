package com.macbitsgoa.comrades.useractivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.macbitsgoa.comrades.EditDeleteDialog;
import com.macbitsgoa.comrades.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_COURSE_ADDED;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_COURSE_RENAMED;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_FILE_ADDED;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_FILE_RENAMED;

class UserActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageAction;
    private ImageView btnDelete;
    private ImageView btnEdit;
    private TextView timeStamp;
    private TextView textMain;
    private UserActivityModel userActivityModel;


    public UserActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        imageAction = itemView.findViewById(R.id.image_action);
        btnDelete = itemView.findViewById(R.id.btn_delete);
        btnEdit = itemView.findViewById(R.id.btn_edit);
        timeStamp = itemView.findViewById(R.id.time);
        textMain = itemView.findViewById(R.id.text_main);
    }

    public void populate(UserActivityModel userActivityModel) {
        this.userActivityModel = userActivityModel;

        if (userActivityModel.getType().equals(ACTION_COURSE_ADDED)) {
            imageAction.setImageResource(R.drawable.add_course);
            imageAction.setColorFilter(R.color.colorPrimary);
            btnDelete.setVisibility(View.GONE);
        } else if (userActivityModel.getType().equals(ACTION_COURSE_RENAMED)) {
            imageAction.setImageResource(R.drawable.file_edit_white_outline);
            imageAction.setColorFilter(R.color.colorPrimary);
            btnDelete.setVisibility(View.GONE);
        } else if (userActivityModel.getType().equals(ACTION_FILE_RENAMED)) {
            imageAction.setImageResource(R.drawable.file_edit_white_outline);
            imageAction.setColorFilter(R.color.colorPrimary);
            btnDelete.setVisibility(View.GONE);
        } else if (userActivityModel.getType().equals(ACTION_FILE_ADDED)) {
            imageAction.setImageResource(R.drawable.ic_add_file_full);
            imageAction.setColorFilter(R.color.colorPrimary);
            btnDelete.setOnClickListener(this);
        }

        btnEdit.setOnClickListener(this);
        textMain.setText(userActivityModel.getMessage());
        SimpleDateFormat sfd = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
        timeStamp.setText(sfd.format(new Date(userActivityModel.getTimeStamp())));
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.btn_delete:
                break;

            case R.id.btn_edit:
                EditDeleteDialog.newInstance(ACTION_COURSE_RENAMED, userActivityModel.getName(),
                        userActivityModel.getCode(), null, userActivityModel.get_id())
                        .show(fm.beginTransaction(), "editCourseFragment");
                break;
            default:
                break;

        }


    }
}
