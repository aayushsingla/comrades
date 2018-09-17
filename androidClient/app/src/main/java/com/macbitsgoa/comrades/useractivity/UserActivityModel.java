package com.macbitsgoa.comrades.useractivity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class UserActivityModel {

    //courseId for courses and file Id for files
    @NonNull
    @ColumnInfo(name = "_id")
    public String _id;
    @PrimaryKey(autoGenerate = true)
    public Long primaryKey;
    //only available for files.For courses _id=courseId
    @ColumnInfo(name = "courseId")
    public String courseId;
    @ColumnInfo(name = "hashId")
    public String hashId;
    //course name for courses and file name for files
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "filePath")
    public String filePath;
    //type of action: fileAdded, courseAdded, fileRenamed, courseRenamed, courseDeleted
    @ColumnInfo(name = "type")
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
    @ColumnInfo(name = "message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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




