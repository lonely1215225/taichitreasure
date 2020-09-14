package edu.hunau.crowd.entity.vo;

public class DetailReturnVO {
    //     回报信息主键
    private Integer returnId;
    // 当前档位需支持的金额
    private Integer supportMoney;
    // 单笔限购，取值为 0 时无限额，取值为 1 时有限额
    private Integer singlePurchase;
    // 具体限额数量
    private Integer purchase;
    // 当前该档位支持者数量
    private Integer supporterCount=999;
    //     运费，取值为 0 时表示包邮
    private Integer freight;
    // 众筹成功后多少天发货
    private Integer returnDate;
    // 回报内容
    private String content;

    public DetailReturnVO() {
    }

    public DetailReturnVO(Integer returnId, Integer supportMoney, Integer singlePurchase, Integer purchase, Integer supporterCount, Integer freight, Integer returnDate, String content) {
        this.returnId = returnId;
        this.supportMoney = supportMoney;
        this.singlePurchase = singlePurchase;
        this.purchase = purchase;
        this.supporterCount = supporterCount;
        this.freight = freight;
        this.returnDate = returnDate;
        this.content = content;
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Integer getSinglePurchase() {
        return singlePurchase;
    }

    public void setSinglePurchase(Integer singlePurchase) {
        this.singlePurchase = singlePurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getSupporterCount() {
        return supporterCount;
    }

    public void setSupporterCount(Integer supporterCount) {
        this.supporterCount = supporterCount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Integer returnDate) {
        this.returnDate = returnDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
