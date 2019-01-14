package com.example.admin.tourproduct.entry;

public class UserCoupon {


    /**
     * Id : 1
     * ShopId : 2
     * UserId : sample string 3
     * Name : sample string 4
     * Content : sample string 5
     * termOfValidity : sample string 6
     * time : sample string 7
     * userule : sample string 8
     * Status : sample string 9
     * Limit : sample string 10
     * UniqueCode : sample string 11
     * denomination : sample string 12
     * money : 13.1
     */

    private int Id;
    private int ShopId;
    private String UserId;
    private String Name;
    private String Content;
    private String termOfValidity;
    private String time;
    private String userule;
    private String Status;
    private String Limit;
    private String UniqueCode;
    private String denomination;
    private double money;

    public UserCoupon(int id, int shopId, String userId, String name, String content, String termOfValidity, String time, String userule, String status, String limit, String uniqueCode, String denomination, double money) {
        Id = id;
        ShopId = shopId;
        UserId = userId;
        Name = name;
        Content = content;
        this.termOfValidity = termOfValidity;
        this.time = time;
        this.userule = userule;
        Status = status;
        Limit = limit;
        UniqueCode = uniqueCode;
        this.denomination = denomination;
        this.money = money;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int ShopId) {
        this.ShopId = ShopId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(String termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserule() {
        return userule;
    }

    public void setUserule(String userule) {
        this.userule = userule;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getLimit() {
        return Limit;
    }

    public void setLimit(String Limit) {
        this.Limit = Limit;
    }

    public String getUniqueCode() {
        return UniqueCode;
    }

    public void setUniqueCode(String UniqueCode) {
        this.UniqueCode = UniqueCode;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
