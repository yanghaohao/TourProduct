package com.example.admin.tourproduct.entry;

import java.io.Serializable;
import java.util.List;

//首页和美食界面的自动轮播广告
public class Advertisement implements Serializable{
    /**
     * Status : 1
     * Message : sample string 2
     * NewID : {}
     * Entity : [{"Id":1,"introduce":"sample string 2","Type":3,"drawableRes":"sample string 4","AD":5,"Url":"sample string 6"},{"Id":1,"introduce":"sample string 2","Type":3,"drawableRes":"sample string 4","AD":5,"Url":"sample string 6"}]
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
         * Id : 1
         * introduce : sample string 2
         * Type : 3
         * drawableRes : sample string 4
         * AD : 5
         * Url : sample string 6
         */

        private int Id;
        private String introduce;
        private int Type;
        private String drawableRes;
        private int AD;
        private String Url;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getDrawableRes() {
            return drawableRes;
        }

        public void setDrawableRes(String drawableRes) {
            this.drawableRes = drawableRes;
        }

        public int getAD() {
            return AD;
        }

        public void setAD(int AD) {
            this.AD = AD;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }


//    //广告类型，0表示自带链接广告，1表示不带链接广告
//    private int type;
//    //图片
//    private int drawableRes;
//    //这个字段0表示没有文案，其余的作为不带链接广告的id（我的设计是吧我们自己的不带连接的广告当成是攻略来处理这样大家都省事）
//    //在上传广告的时候就直接给一个框填写id就行了，所以需要在后台维护平台显示出来当前的攻略id是多少，没有就填0，这个我会说明的
//    private int AD;
//    //url本地判断如果没有文案，或者广告类型为0，这个字段才会引用
//    private String url;
//
//    public Advertisement(int type, int drawableRes) {
//        this.type = type;
//        this.drawableRes = drawableRes;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public int getDrawableRes() {
//        return drawableRes;
//    }
//
//    public void setDrawableRes(int drawableRes) {
//        this.drawableRes = drawableRes;
//    }
//
//    public int getAD() {
//        return AD;
//    }
//
//    public void setAD(int AD) {
//        this.AD = AD;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
}
