package com.example.admin.tourproduct.entry;

public class Type {

    private String type;
    private String fileType;

    public Type(String type, String fileType) {
        this.type = type;
        this.fileType = fileType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
