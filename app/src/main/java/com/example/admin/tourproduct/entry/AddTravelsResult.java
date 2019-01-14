package com.example.admin.tourproduct.entry;

public class AddTravelsResult {


    /**
     * Status : 1
     * Message : null
     * NewID : 4
     * ExtraData : null
     */

    private int Status;
    private Object Message;
    private int NewID;
    private Object ExtraData;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public int getNewID() {
        return NewID;
    }

    public void setNewID(int NewID) {
        this.NewID = NewID;
    }

    public Object getExtraData() {
        return ExtraData;
    }

    public void setExtraData(Object ExtraData) {
        this.ExtraData = ExtraData;
    }
}
