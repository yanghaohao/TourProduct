package com.example.admin.tourproduct.entry;

public class Discuss {
    /**
     * Id : 1
     * TNId : 2
     * content : sample string 3
     * inputuser : sample string 4
     * inputdate : sample string 5
     */

    private int Id;
    private int TNId;
    private String content;
    private String inputuser;
    private String inputdate;

    public Discuss(int id, int TNId, String content, String inputuser, String inputdate) {
        Id = id;
        this.TNId = TNId;
        this.content = content;
        this.inputuser = inputuser;
        this.inputdate = inputdate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getTNId() {
        return TNId;
    }

    public void setTNId(int TNId) {
        this.TNId = TNId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInputuser() {
        return inputuser;
    }

    public void setInputuser(String inputuser) {
        this.inputuser = inputuser;
    }

    public String getInputdate() {
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }
}
