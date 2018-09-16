package com.macbitsgoa.comrades;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.macbitsgoa.comrades.useractivity.UserActivityModel;
import com.macbitsgoa.comrades.useractivity.UserActivityVm;

import java.util.Calendar;
import java.util.Objects;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import static com.macbitsgoa.comrades.CHCKt.TAG_PREFIX;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_COURSE_RENAMED;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_FILE_RENAMED;

public class EditDeleteDialog extends DialogFragment {

    private static final String TAG = TAG_PREFIX + EditDeleteDialog.class.getSimpleName();
    private TextInputEditText etCourseName;
    private TextInputEditText etStreamCode;
    private TextInputEditText etCourseNumber;
    private DialogInterface.OnClickListener positiveClickListener;
    private String action;
    private String id;

    public static EditDeleteDialog newInstance(String action, String courseName, String courseCode,
                                               String fileName, String id) {
        EditDeleteDialog editDeleteDialog = new EditDeleteDialog();
        Bundle args = new Bundle();
        args.putString("action", action);
        args.putString("courseName", courseName);
        args.putString("courseCode", courseCode);
        args.putString("fileName", fileName);
        args.putString("id", id);
        editDeleteDialog.setArguments(args);
        return editDeleteDialog;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            action = getArguments().getString("action");
            id = getArguments().getString("id");
        } else {
            Log.e(TAG, "id and action tags cannot be null");
        }

        View view = null;
        if (action.matches(ACTION_COURSE_RENAMED)) {
            view = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.activity_add_course, null);

            etCourseName = view.findViewById(R.id.et_course_name);
            etCourseNumber = view.findViewById(R.id.et_course_number);
            etStreamCode = view.findViewById(R.id.et_stream_code);

            String courseCode = getArguments().getString("courseCode");
            String[] stringArrayList = courseCode.split("-");

            etCourseName.setText(getArguments().getString("courseName"));
            etStreamCode.setText(stringArrayList[0]);
            etCourseNumber.setText(stringArrayList[1]);

        } else if (action.matches(ACTION_FILE_RENAMED)) {
            view = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.activity_add_course, null);

        }

        setPostiveClick();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setPositiveButton("Add Course", positiveClickListener)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
        return builder.create();

    }


    private void setPostiveClick() {
        positiveClickListener = (dialog, which) -> {
            if (action.matches(ACTION_COURSE_RENAMED)) {
                courseNameChanged();
            } else if (action.matches(ACTION_FILE_RENAMED)) {
                fileNameChanged();
            }
        };

    }

    private void fileNameChanged() {

    }

    private void courseNameChanged() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE)
                .child("courses").child(id);
        String courseName = etCourseName.getText().toString();
        String courseCode = etStreamCode.getText() + "-" + etCourseNumber.getText();

        reference.child("name").setValue(courseName);
        reference.child("code").setValue(courseCode);

        UserActivityVm userActivityVm = ViewModelProviders.of(this).get(UserActivityVm.class);
        UserActivityModel userActivityModel = new UserActivityModel();
        userActivityModel.setId(id);
        userActivityModel.setName(courseName);
        userActivityModel.setMessage("Renamed course to " + courseName + " and course Id to " + courseCode);
        userActivityModel.setCode(courseCode);
        userActivityModel.setTimeStamp(Calendar.getInstance().getTimeInMillis());
        userActivityModel.set_id(id);
        userActivityModel.setType(ACTION_COURSE_RENAMED);
        userActivityVm.insertRecent(userActivityModel);

    }

}