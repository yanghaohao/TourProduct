package com.example.admin.tourproduct.entry;

import java.io.Serializable;
import java.util.List;

public class TravelsNote implements Serializable{


    /**
     * Status : 1
     * Message : sample string 2
     * NewID : {}
     * Entity : [{"Bitmaps":[{"Id":1,"TNId":2,"bitmap":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"},{"Id":1,"TNId":2,"bitmap":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"}],"Discuss":[{"Id":1,"TNId":2,"content":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"},{"Id":1,"TNId":2,"content":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"}],"Id":1,"name":"sample string 2","head":"sample string 3","content":"sample string 4","inputuser":"sample string 5","inputdate":"sample string 6","numberOfGiveTheThumbsUp":7},{"Bitmaps":[{"Id":1,"TNId":2,"bitmap":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"},{"Id":1,"TNId":2,"bitmap":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"}],"Discuss":[{"Id":1,"TNId":2,"content":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"},{"Id":1,"TNId":2,"content":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"}],"Id":1,"name":"sample string 2","head":"sample string 3","content":"sample string 4","inputuser":"sample string 5","inputdate":"sample string 6","numberOfGiveTheThumbsUp":7}]
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
         * Bitmaps : [{"Id":1,"TNId":2,"bitmap":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"},{"Id":1,"TNId":2,"bitmap":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"}]
         * Discuss : [{"Id":1,"TNId":2,"content":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"},{"Id":1,"TNId":2,"content":"sample string 3","inputuser":"sample string 4","inputdate":"sample string 5"}]
         * Id : 1
         * name : sample string 2
         * head : sample string 3
         * content : sample string 4
         * inputuser : sample string 5
         * inputdate : sample string 6
         * numberOfGiveTheThumbsUp : 7
         */

        private int Id;
        private String name;
        private String head;
        private String content;
        private String inputuser;
        private String inputdate;
        private int numberOfGiveTheThumbsUp;
        private List<BitmapsBean> Bitmaps;
        private List<DiscussBean> Discuss;
        private List<String> bitmapPath;

        public EntityBean(int id, String name, String head, String content, String inputuser, String inputdate, int numberOfGiveTheThumbsUp, List<String> bitmapPath, List<DiscussBean> discuss) {
            Id = id;
            this.name = name;
            this.head = head;
            this.content = content;
            this.inputuser = inputuser;
            this.inputdate = inputdate;
            this.numberOfGiveTheThumbsUp = numberOfGiveTheThumbsUp;
            this.bitmapPath = bitmapPath;
            Discuss = discuss;
        }

        public List<String> getBitmapPath() {
            return bitmapPath;
        }

        public void setBitmapPath(List<String> bitmapPath) {
            this.bitmapPath = bitmapPath;
        }

        public EntityBean(int id) {
            Id = id;
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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
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

        public int getNumberOfGiveTheThumbsUp() {
            return numberOfGiveTheThumbsUp;
        }

        public void setNumberOfGiveTheThumbsUp(int numberOfGiveTheThumbsUp) {
            this.numberOfGiveTheThumbsUp = numberOfGiveTheThumbsUp;
        }

        public List<BitmapsBean> getBitmaps() {
            return Bitmaps;
        }

        public void setBitmaps(List<BitmapsBean> Bitmaps) {
            this.Bitmaps = Bitmaps;
        }

        public List<DiscussBean> getDiscuss() {
            return Discuss;
        }

        public void setDiscuss(List<DiscussBean> Discuss) {
            this.Discuss = Discuss;
        }

        public static class BitmapsBean implements Serializable{
            /**
             * Id : 1
             * TNId : 2
             * bitmap : sample string 3
             * inputuser : sample string 4
             * inputdate : sample string 5
             */

            private int Id;
            private int TNId;
            private String bitmap;
            private String inputuser;
            private String inputdate;

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

            public String getBitmap() {
                return bitmap;
            }

            public void setBitmap(String bitmap) {
                this.bitmap = bitmap;
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

        public static class DiscussBean implements Serializable{
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

            public DiscussBean(int id, int TNId, String content, String inputuser, String inputdate) {
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
    }
}
