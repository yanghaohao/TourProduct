package com.example.admin.tourproduct.entry;

import java.util.List;

public class Travels {

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

    public Travels(int id, String name, String head, String content, String inputuser, String inputdate, int numberOfGiveTheThumbsUp, List<BitmapsBean> bitmaps, List<DiscussBean> discuss) {
        Id = id;
        this.name = name;
        this.head = head;
        this.content = content;
        this.inputuser = inputuser;
        this.inputdate = inputdate;
        this.numberOfGiveTheThumbsUp = numberOfGiveTheThumbsUp;
        Bitmaps = bitmaps;
        Discuss = discuss;
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

    public static class BitmapsBean {
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

    public static class DiscussBean {
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
