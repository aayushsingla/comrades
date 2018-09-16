package com.macbitsgoa.comrades.UserActivity;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class UserActivityModel {


    public class CourseMaterial {

        @PrimaryKey  //courseId for courses and file Id for files
        @NonNull
        @ColumnInfo(name = "_id")
        public String _id;

        @NonNull
        @ColumnInfo(name = "courseId")
        public String courseId;
        @ColumnInfo(name = "name") //course name for courses and file name for files
        @NonNull
        public String name;
        @ColumnInfo(name = "filePath")
        public String filePath;
        @ColumnInfo(name = "type") //File or Course
        public String type;
        @ColumnInfo(name = "code")
        private String code;
        @ColumnInfo(name = "timeStamp")
        private Long timeStamp;
        @ColumnInfo(name = "mimeType")
        private String mimeType;
        @ColumnInfo(name = "extension")
        private String extension;
        @ColumnInfo(name = "iconLink")
        private String iconLink;

        public String getId() {
            return _id;
        }

        public void setId(String id) {
            this._id = id;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(@NotNull String course_id) {
            this.courseId = course_id;
        }

        @NonNull
        public String getName() {
            return name;
        }

        public void setName(@NonNull String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @NonNull
        public String get_id() {
            return _id;
        }

        public void set_id(@NonNull String _id) {
            this._id = _id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getIconLink() {
            return iconLink;
        }

        public void setIconLink(String iconLink) {
            this.iconLink = iconLink;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
        }
    }


}
