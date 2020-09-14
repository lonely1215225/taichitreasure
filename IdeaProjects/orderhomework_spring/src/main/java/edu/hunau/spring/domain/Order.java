package edu.hunau.spring.domain;

public class Order {
    private int id;
    private int cid;
    private double value;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cid=" + cid +
                ", value=" + value +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
