package com.example.admin.tourproduct.entry;

/**
 * @Author: Bro0cL
 * @Date: 2018/2/6 17:52
 */
public class User {


    /**
     * Status : 1
     * Message : sample string 2
     * NewID : {}
     * Entity : {"ID":1,"LoginName":"sample string 2","UserName":"sample string 3","AccountType":4,"Status":5,"Password":"sample string 6","PasswordFormat":7,"PasswordSalt":"sample string 8","EMail":"sample string 9"}
     * TotalPages : 4
     * TotalRowsCount : 5
     */

    private int Status;
    private String Message;
    private NewIDBean NewID;
    private EntityBean Entity;
    private int TotalPages;
    private int TotalRowsCount;

    public User(EntityBean entity) {
        Entity = entity;
    }

    @Override
    public String toString() {
        return "User{" +
                "Status=" + Status +
                ", Message='" + Message + '\'' +
                ", NewID=" + NewID +
                ", Entity=" + Entity +
                ", TotalPages=" + TotalPages +
                ", TotalRowsCount=" + TotalRowsCount +
                '}';
    }

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

    public NewIDBean getNewID() {
        return NewID;
    }

    public void setNewID(NewIDBean NewID) {
        this.NewID = NewID;
    }

    public EntityBean getEntity() {
        return Entity;
    }

    public void setEntity(EntityBean Entity) {
        this.Entity = Entity;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int TotalPages) {
        this.TotalPages = TotalPages;
    }

    public int getTotalRowsCount() {
        return TotalRowsCount;
    }

    public void setTotalRowsCount(int TotalRowsCount) {
        this.TotalRowsCount = TotalRowsCount;
    }

    public static class NewIDBean {
    }

    public static class EntityBean {
        /**
         * ID : 1
         * LoginName : sample string 2
         * UserName : sample string 3
         * AccountType : 4
         * Status : 5
         * Password : sample string 6
         * PasswordFormat : 7
         * PasswordSalt : sample string 8
         * EMail : sample string 9
         */

        private int ID;
        private String LoginName;
        private String UserName;
        private int AccountType;
        private int Status;
        private String Password;
        private int PasswordFormat;
        private String PasswordSalt;
        private String EMail;

        public EntityBean(int ID) {
            this.ID = ID;
        }

        public EntityBean(String loginName, String userName, String password) {
            LoginName = loginName;
            UserName = userName;
            Password = password;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getLoginName() {
            return LoginName;
        }

        public void setLoginName(String LoginName) {
            this.LoginName = LoginName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public int getAccountType() {
            return AccountType;
        }

        public void setAccountType(int AccountType) {
            this.AccountType = AccountType;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public int getPasswordFormat() {
            return PasswordFormat;
        }

        public void setPasswordFormat(int PasswordFormat) {
            this.PasswordFormat = PasswordFormat;
        }

        public String getPasswordSalt() {
            return PasswordSalt;
        }

        public void setPasswordSalt(String PasswordSalt) {
            this.PasswordSalt = PasswordSalt;
        }

        public String getEMail() {
            return EMail;
        }

        public void setEMail(String EMail) {
            this.EMail = EMail;
        }
    }
}
