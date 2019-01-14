package com.example.admin.tourproduct.entry;

import java.util.List;

/**
 * @Author: Bro0cL
 * @Date: 2018/2/6 20:50
 */
public class Notice {
    /**
     * Status : 1
     * Message : sample string 2
     * NewID : {}
     * Entity : [{"Id":1,"isRead":"sample string 2","Sender":"sample string 3","Receiver":"sample string 4","Title":"sample string 5","MesContent":"sample string 6","InputDate":"sample string 7"},{"Id":1,"isRead":"sample string 2","Sender":"sample string 3","Receiver":"sample string 4","Title":"sample string 5","MesContent":"sample string 6","InputDate":"sample string 7"}]
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

    public static class NewIDBean {
    }

    public static class EntityBean {
        /**
         * Id : 1
         * isRead : sample string 2
         * Sender : sample string 3
         * Receiver : sample string 4
         * Title : sample string 5
         * MesContent : sample string 6
         * InputDate : sample string 7
         */

        private int Id;
        private String isRead;
        private String Sender;
        private String Receiver;
        private String Title;
        private String MesContent;
        private String InputDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
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

        public void setSender(String Sender) {
            this.Sender = Sender;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String Receiver) {
            this.Receiver = Receiver;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getMesContent() {
            return MesContent;
        }

        public void setMesContent(String MesContent) {
            this.MesContent = MesContent;
        }

        public String getInputDate() {
            return InputDate;
        }

        public void setInputDate(String InputDate) {
            this.InputDate = InputDate;
        }
    }


//    //用户的头像，想在还有没，这个字段就先放弃
//    private int userIcon;
//    //用户的名字
//    private String name;
//    //内容
//    private String content;
//    //是否已读
//    private boolean isRead;
//    //类型，0为通知消息，1为评论消息
//    private int type;
//    //如果是评论消息，那这个字段就会返回游记ID,0代表不是游记id
//    private int travelId;

}
