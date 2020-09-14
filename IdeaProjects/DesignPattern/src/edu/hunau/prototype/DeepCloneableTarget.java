package edu.hunau.prototype;

import java.io.Serializable;

public class DeepCloneableTarget  implements Cloneable,Serializable{
    private static final long serialVersionUID=1L;
    private String cloneName;
    private String cloneClass;
    private int i=4;
    private int[] a;

    public DeepCloneableTarget(){}
    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    //此类只有String类型，所以直接调用object的clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setCloneName(String cloneName) {
        this.cloneName = cloneName;
    }

    public void setCloneClass(String cloneClass) {
        this.cloneClass = cloneClass;
    }

    @Override
    public String toString() {
        return "DeepCloneableTarget{" +
                "cloneName='" + cloneName + '\'' +
                ", cloneClass='" + cloneClass + '\'' +
                '}';
    }
}
