package com.example.admin.tourproduct.entry;

public class CommercialTenant {

    //商家图片
    private String drawableURL;
    //名字
    private String commercialTenantName;
    //商户评分
    private int starLevel;
    //电话
    private String phoneNumber;
    //地点
    private String address;
    //营业时间
    private String time;
    //优惠券(这个一个对象json对象怎么包含我写不来我就在注释写一下)
    /*
        这个是优惠券的对象
        列如：菜品的对象：string name
                          这个是代指，是那个什么二选一的，如果是多个字段忽略这个
                          string content
                          有效期
                          string termOfValidity
                          使用的时间
                          string time
                          使用规则
                          string rule
     */
    //评论(这是一个数组)
}
