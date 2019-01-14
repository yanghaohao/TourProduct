package com.example.admin.tourproduct.entry;

public class Image {

    private String fileName;

    private String fromName;

    private String contentType;

    private byte[] data;

    public Image(String fileName, String fromName) {
        this.fileName = fileName;
        this.fromName = fromName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormName() {
        return fromName;
    }

    public void setFormName(String fromName) {
        this.fromName = fromName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] dara) {
        this.data = dara;
    }
}
