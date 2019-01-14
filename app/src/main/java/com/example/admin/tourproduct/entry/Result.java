package com.example.admin.tourproduct.entry;

public class Result {

    /**
     * Status : 1
     * Message : 操作成功
     * NewID : null
     * ExtraData : null
     */

    private int Status;
    private String Message;
    private Object NewID;
    private Object ExtraData;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Object getNewID() {
        return NewID;
    }

    public void setNewID(Object NewID) {
        this.NewID = NewID;
    }

    public Object getExtraData() {
        return ExtraData;
    }

    public void setExtraData(Object ExtraData) {
        this.ExtraData = ExtraData;
    }
}
