package com.example.admin.tourproduct.entry;

public class MessagePost {

    private String isRead;
    private String Sender;
    private String Receiver;
    private String Title;
    private String MesContent ;
    private String InputDate;

    public MessagePost(String isRead, String sender, String receiver, String title, String mesContent, String inputDate) {
        this.isRead = isRead;
        Sender = sender;
        Receiver = receiver;
        Title = title;
        MesContent = mesContent;
        InputDate = inputDate;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMesContent() {
        return MesContent;
    }

    public void setMesContent(String mesContent) {
        MesContent = mesContent;
    }

    public String getInputDate() {
        return InputDate;
    }

    public void setInputDate(String inputDate) {
        InputDate = inputDate;
    }
}
