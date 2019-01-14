package com.example.admin.tourproduct.entry;

public class Version {


    /**
     * Status : 1
     * Message : null
     * NewID : null
     * Entity : {"Id":2,"Res":"/UpFiles/VersionFiles/20180716/e9eb98e9c6e345cd94fb4ba2bc98e8ff.apk","FileName":"安卓客户端第一版","VersionNum":"V1.0.1"}
     * TotalPages : 0
     * TotalRowsCount : 0
     */

    private int Status;
    private Object Message;
    private Object NewID;
    private EntityBean Entity;
    private int TotalPages;
    private int TotalRowsCount;

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

    public Object getNewID() {
        return NewID;
    }

    public void setNewID(Object NewID) {
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

    public static class EntityBean {
        /**
         * Id : 2
         * Res : /UpFiles/VersionFiles/20180716/e9eb98e9c6e345cd94fb4ba2bc98e8ff.apk
         * FileName : 安卓客户端第一版
         * VersionNum : V1.0.1
         */

        private int Id;
        private String Res;
        private String FileName;
        private String VersionNum;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getRes() {
            return Res;
        }

        public void setRes(String Res) {
            this.Res = Res;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getVersionNum() {
            return VersionNum;
        }

        public void setVersionNum(String VersionNum) {
            this.VersionNum = VersionNum;
        }
    }
}
