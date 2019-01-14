package com.example.admin.tourproduct.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 美食
 */
public class Delicacy implements Serializable{

    /**
     * Status : 1
     * Message : sample string 2
     * NewID : {}
     * Entity : [{"CommentList":[{"Id":1,"ShopId":2,"drawableURL":"sample string 3","commentUser":"sample string 4","Content":"sample string 5","Title":"sample string 6","Star":7.1,"Status":"sample string 8"},{"Id":1,"ShopId":2,"drawableURL":"sample string 3","commentUser":"sample string 4","Content":"sample string 5","Title":"sample string 6","Star":7.1,"Status":"sample string 8"}],"CouponList":[{"Id":1,"ShopId":2,"UserId":"sample string 3","Name":"sample string 4","Content":"sample string 5","termOfValidity":"sample string 6","time":"sample string 7","userule":"sample string 8","Status":"sample string 9","Limit":"sample string 10","UniqueCode":"sample string 11","denomination":"sample string 12","money":13.1},{"Id":1,"ShopId":2,"UserId":"sample string 3","Name":"sample string 4","Content":"sample string 5","termOfValidity":"sample string 6","time":"sample string 7","userule":"sample string 8","Status":"sample string 9","Limit":"sample string 10","UniqueCode":"sample string 11","denomination":"sample string 12","money":13.1}],"Id":1,"drawableURL":"sample string 2","HotPoint":3,"commercialTenantName":"sample string 4","starLevel":5.1,"phoneNumber":"sample string 6","address":"sample string 7","time":"sample string 8"},{"CommentList":[{"Id":1,"ShopId":2,"drawableURL":"sample string 3","commentUser":"sample string 4","Content":"sample string 5","Title":"sample string 6","Star":7.1,"Status":"sample string 8"},{"Id":1,"ShopId":2,"drawableURL":"sample string 3","commentUser":"sample string 4","Content":"sample string 5","Title":"sample string 6","Star":7.1,"Status":"sample string 8"}],"CouponList":[{"Id":1,"ShopId":2,"UserId":"sample string 3","Name":"sample string 4","Content":"sample string 5","termOfValidity":"sample string 6","time":"sample string 7","userule":"sample string 8","Status":"sample string 9","Limit":"sample string 10","UniqueCode":"sample string 11","denomination":"sample string 12","money":13.1},{"Id":1,"ShopId":2,"UserId":"sample string 3","Name":"sample string 4","Content":"sample string 5","termOfValidity":"sample string 6","time":"sample string 7","userule":"sample string 8","Status":"sample string 9","Limit":"sample string 10","UniqueCode":"sample string 11","denomination":"sample string 12","money":13.1}],"Id":1,"drawableURL":"sample string 2","HotPoint":3,"commercialTenantName":"sample string 4","starLevel":5.1,"phoneNumber":"sample string 6","address":"sample string 7","time":"sample string 8"}]
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
         * CommentList : [{"Id":1,"ShopId":2,"drawableURL":"sample string 3","commentUser":"sample string 4","Content":"sample string 5","Title":"sample string 6","Star":7.1,"Status":"sample string 8"},{"Id":1,"ShopId":2,"drawableURL":"sample string 3","commentUser":"sample string 4","Content":"sample string 5","Title":"sample string 6","Star":7.1,"Status":"sample string 8"}]
         * CouponList : [{"Id":1,"ShopId":2,"UserId":"sample string 3","Name":"sample string 4","Content":"sample string 5","termOfValidity":"sample string 6","time":"sample string 7","userule":"sample string 8","Status":"sample string 9","Limit":"sample string 10","UniqueCode":"sample string 11","denomination":"sample string 12","money":13.1},{"Id":1,"ShopId":2,"UserId":"sample string 3","Name":"sample string 4","Content":"sample string 5","termOfValidity":"sample string 6","time":"sample string 7","userule":"sample string 8","Status":"sample string 9","Limit":"sample string 10","UniqueCode":"sample string 11","denomination":"sample string 12","money":13.1}]
         * Id : 1
         * drawableURL : sample string 2
         * HotPoint : 3
         * commercialTenantName : sample string 4
         * starLevel : 5.1
         * phoneNumber : sample string 6
         * address : sample string 7
         * time : sample string 8
         */

        private int Id;
        private String drawableURL;
        private int HotPoint;
        private String commercialTenantName;
        private double starLevel;
        private String phoneNumber;
        private String address;
        private String time;
        private List<CommentListBean> CommentList;
        private List<CouponListBean> CouponList;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getDrawableURL() {
            return drawableURL;
        }

        public void setDrawableURL(String drawableURL) {
            this.drawableURL = drawableURL;
        }

        public int getHotPoint() {
            return HotPoint;
        }

        public void setHotPoint(int HotPoint) {
            this.HotPoint = HotPoint;
        }

        public String getCommercialTenantName() {
            return commercialTenantName;
        }

        public void setCommercialTenantName(String commercialTenantName) {
            this.commercialTenantName = commercialTenantName;
        }

        public double getStarLevel() {
            return starLevel;
        }

        public void setStarLevel(double starLevel) {
            this.starLevel = starLevel;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<CommentListBean> getCommentList() {
            return CommentList;
        }

        public void setCommentList(List<CommentListBean> CommentList) {
            this.CommentList = CommentList;
        }

        public List<CouponListBean> getCouponList() {
            return CouponList;
        }

        public void setCouponList(List<CouponListBean> CouponList) {
            this.CouponList = CouponList;
        }

        public static class CommentListBean implements Serializable{
            /**
             * Id : 1
             * ShopId : 2
             * drawableURL : sample string 3
             * commentUser : sample string 4
             * Content : sample string 5
             * Title : sample string 6
             * Star : 7.1
             * Status : sample string 8
             */

            private int Id;
            private int ShopId;
            private String drawableURL;
            private String commentUser;
            private String Content;
            private String Title;
            private double Star;
            private String Status;

            public CommentListBean(int id, int shopId, String drawableURL, String commentUser, String content, String title, double star, String status) {
                Id = id;
                ShopId = shopId;
                this.drawableURL = drawableURL;
                this.commentUser = commentUser;
                Content = content;
                Title = title;
                Star = star;
                Status = status;
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

            public String getDrawableURL() {
                return drawableURL;
            }

            public void setDrawableURL(String drawableURL) {
                this.drawableURL = drawableURL;
            }

            public String getCommentUser() {
                return commentUser;
            }

            public void setCommentUser(String commentUser) {
                this.commentUser = commentUser;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public double getStar() {
                return Star;
            }

            public void setStar(double Star) {
                this.Star = Star;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }
        }

        public static class CouponListBean implements Serializable{
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
            private int bitmap;

            public CouponListBean(int id, int shopId, String userId, String name, String content, String termOfValidity, String time, String userule, String status, String limit, String uniqueCode, String denomination, double money, int bitmap) {
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
                this.bitmap = bitmap;
            }

            public int getBitmap() {
                return bitmap;
            }

            public void setBitmap(int bitmap) {
                this.bitmap = bitmap;
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

            @Override
            public String toString() {
                return "CouponListBean{" +
                        "Id=" + Id +
                        ", ShopId=" + ShopId +
                        ", UserId='" + UserId + '\'' +
                        ", Name='" + Name + '\'' +
                        ", Content='" + Content + '\'' +
                        ", termOfValidity='" + termOfValidity + '\'' +
                        ", time='" + time + '\'' +
                        ", userule='" + userule + '\'' +
                        ", Status='" + Status + '\'' +
                        ", Limit='" + Limit + '\'' +
                        ", UniqueCode='" + UniqueCode + '\'' +
                        ", denomination='" + denomination + '\'' +
                        ", money=" + money +
                        ", bitmap=" + bitmap +
                        '}';
            }
        }
    }
}
