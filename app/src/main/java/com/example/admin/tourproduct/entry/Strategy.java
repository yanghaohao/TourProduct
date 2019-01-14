package com.example.admin.tourproduct.entry;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YH on 2018/5/3.
 */

public class Strategy implements Serializable {


    /**
     * Status : 1
     * Message : sample string 2
     * NewID : {}
     * Entity : [{"discusses":[{},{}],"Id":1,"name":"sample string 2","headSculpture":"sample string 3","introduce":"sample string 4","content":"sample string 5","time":"sample string 6","panorama":"sample string 7"},{"discusses":[{},{}],"Id":1,"name":"sample string 2","headSculpture":"sample string 3","introduce":"sample string 4","content":"sample string 5","time":"sample string 6","panorama":"sample string 7"}]
     * TotalPages : 4
     * TotalRowsCount : 5
     */

    private int Status;
    private String Message;
    private NewIDBean NewID;
    private int TotalPages;
    private int TotalRowsCount;
    private List<EntityBean> Entity;

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

    public List<EntityBean> getEntity() {
        return Entity;
    }

    public void setEntity(List<EntityBean> Entity) {
        this.Entity = Entity;
    }

    public static class NewIDBean implements Serializable{
    }

    public static class EntityBean implements Serializable{
        /**
         * discusses : [{},{}]
         * Id : 1
         * name : sample string 2
         * headSculpture : sample string 3
         * introduce : sample string 4
         * content : sample string 5
         * time : sample string 6
         * panorama : sample string 7
         */

        private int Id;
        private String name;
        private String headSculpture;
        private String introduce;
        private String content;
        private String time;
        private String panorama;
        private List<DiscussesBean> discusses;
        private Bitmap bitmap;

        public EntityBean(int id) {
            Id = id;
        }

        public EntityBean(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public EntityBean(String name, Bitmap bitmap) {
            this.name = name;
            this.bitmap = bitmap;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadSculpture() {
            return headSculpture;
        }

        public void setHeadSculpture(String headSculpture) {
            this.headSculpture = headSculpture;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPanorama() {
            return panorama;
        }

        public void setPanorama(String panorama) {
            this.panorama = panorama;
        }

        public List<DiscussesBean> getDiscusses() {
            return discusses;
        }

        public void setDiscusses(List<DiscussesBean> discusses) {
            this.discusses = discusses;
        }

        public static class DiscussesBean implements Serializable{
        }
    }
}
