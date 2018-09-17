package com.macbitsgoa.comrades;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.macbitsgoa.comrades.coursematerial.MaterialVm;
import com.macbitsgoa.comrades.useractivity.UserActivityModel;
import com.macbitsgoa.comrades.useractivity.UserActivityVm;

import java.util.Calendar;
import java.util.Objects;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import static com.macbitsgoa.comrades.CHCKt.TAG_PREFIX;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_COURSE_RENAMED;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_FILE_DELETED;
import static com.macbitsgoa.comrades.useractivity.UserActivity.ACTION_FILE_RENAMED;

public class EditDeleteDialog extends DialogFragment {

    private static final String TAG = TAG_PREFIX + EditDeleteDialog.class.getSimpleName();
    private TextInputEditText etCourseName;
    private TextInputEditText etStreamCode;
    private TextInputEditText etCourseNumber;
    private TextInputEditText etFileName;
    private DialogInterface.OnClickListener positiveClickListener;
    private String action;
    private String id;
    private String fileHashId;
    private String courseId;
    private String fileName;
    private MaterialVm materialVm;

    public static EditDeleteDialog newInstance(String action, String courseName, String courseId, String courseCode,
                                               String fileName, String id, String fileHashId) {
        EditDeleteDialog editDeleteDialog = new EditDeleteDialog();
        Bundle args = new Bundle();
        args.putString("action", action);
        args.putString("courseName", courseName);
        args.putString("courseCode", courseCode);
        args.putString("fileName", fileName);
        args.putString("courseId", courseId);
        args.putString("id", id);
        args.putString("fileHashId", fileHashId);
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

        View view;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        setPostiveClick();

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

            builder.setPositiveButton("Rename Course", positiveClickListener);
            builder.setView(view);

        } else if (action.matches(ACTION_FILE_RENAMED)) {
            view = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.layout_edit_file, null);
            courseId = getArguments().getString("courseId");
            fileName = getArguments().getString("fileName");
            fileHashId = getArguments().getString("fileHashId");

            etFileName = view.findViewById(R.id.et_file_name);

            etFileName.setText(fileName);
            builder.setPositiveButton("Rename File", positiveClickListener);
            builder.setView(view);

        } else if (action.matches(ACTION_FILE_DELETED)) {
            courseId = getArguments().getString("courseId");
            fileHashId = getArguments().getString("fileHashId");
            fileName = getArguments().getString("fileName");

            builder.setTitle("Delete File");
            builder.setMessage("This file will be deleted permanently from this app. Click on Delete File to continue.");
            builder.setPositiveButton("Delete File", positiveClickListener);
        }


        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
        return builder.create();

    }


    private void setPostiveClick() {
        positiveClickListener = (dialog, which) -> {
            if (action.matches(ACTION_COURSE_RENAMED)) {
                courseNameChanged();
            } else if (action.matches(ACTION_FILE_RENAMED) || action.matches(ACTION_FILE_DELETED)) {
                checkFileExists();
            }
        };
    }

    private void handleFileDelete() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE)
                .child("courseMaterial").child(courseId).child(fileHashId);
        reference.setValue(null);

        UserActivityVm userActivityVm = ViewModelProviders.of(this).get(UserActivityVm.class);
        UserActivityModel userActivityModel = new UserActivityModel();
        userActivityModel.setCourseId(courseId);
        userActivityModel.setId(id);
        userActivityModel.setHashId(fileHashId);
        userActivityModel.setName(fileName);
        userActivityModel.setMessage("Deleted file " + fileName);
        userActivityModel.setTimeStamp(Calendar.getInstance().getTimeInMillis());
        userActivityModel.set_id(id);
        userActivityModel.setType(ACTION_FILE_DELETED);
        userActivityVm.insertRecent(userActivityModel);

        materialVm.checkFileExists(fileHashId).removeObservers(this);
    }

    private void fileNameChanged() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE)
                .child("courseMaterial").child(courseId).child(fileHashId);
        String newFileName = etFileName.getText().toString();

        reference.child("fileName").setValue(newFileName);

        UserActivityVm userActivityVm = ViewModelProviders.of(this).get(UserActivityVm.class);
        UserActivityModel userActivityModel = new UserActivityModel();
        userActivityModel.setCourseId(courseId);
        userActivityModel.setId(id);
        userActivityModel.setHashId(fileHashId);
        userActivityModel.setName(fileName);
        userActivityModel.setMessage("Renamed file " + fileName + " to " + newFileName);
        userActivityModel.setTimeStamp(Calendar.getInstance().getTimeInMillis());
        userActivityModel.set_id(id);
        userActivityModel.setType(ACTION_FILE_RENAMED);
        userActivityVm.insertRecent(userActivityModel);
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
        userActivityModel.setCourseId(id);
        userActivityModel.setName(courseName);
        userActivityModel.setMessage("Renamed course to " + courseName + " and course Id to " + courseCode);
        userActivityModel.setCode(courseCode);
        userActivityModel.setTimeStamp(Calendar.getInstance().getTimeInMillis());
        userActivityModel.set_id(id);
        userActivityModel.setType(ACTION_COURSE_RENAMED);
        userActivityVm.insertRecent(userActivityModel);

    }

    private void checkFileExists() {
        materialVm = ViewModelProviders.of(this).get(MaterialVm.class);
        materialVm.checkFileExists(fileHashId).observe(this, materials -> {
            Log.e("TAG", materials.size() + "");
            if (materials.size() != 0) {
                if (action.matches(ACTION_FILE_DELETED)) {
                    handleFileDelete();
                } else if (action.matches(ACTION_FILE_RENAMED)) {
                    fileNameChanged();
                }
            } else {
                new AlertDialog.Builder(getContext())
                        .setMessage("The file has already been deleted.")
                        .setTitle("Error")
                        .setPositiveButton("OK", (dialog1, which1) -> dialog1.dismiss())
                        .show();
            }
        });
    }
}