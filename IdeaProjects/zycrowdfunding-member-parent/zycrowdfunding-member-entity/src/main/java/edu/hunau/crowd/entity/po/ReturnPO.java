package edu.hunau.crowd.entity.po;

public class ReturnPO {
    private Integer id;

    private Integer projectId;

    private Integer type;

    private Integer supportMoney;

    private String content;

    private Integer count;

    private Integer singlePurchase;

    private Integer purchase;

    private Integer freight;

    private Integer invoice;

    private Integer returnDate;

    private String describePicPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Integer returnDate) {
        this.returnDate = returnDate;
    }

    public String getDescribePicPath() {
        return describePicPath;
    }

    public void setDescribePicPath(String describePicPath) {
        this.describePicPath = describePicPath == null ? null : describePicPath.trim();
    }
}