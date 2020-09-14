package edu.hunau.prototype;

import java.io.*;

public class DeepProtoType implements Serializable, Cloneable {
    public String name;
    public DeepCloneableTarget deepCloneableTarget;

    //深拷贝1：

    @Override
    protected Object clone() {
        try {
            Object deep = null;

            //这里对基本数据类型和String类型的clone,因为object可以获取this来进行clone，获取this的对象头信息
            deep = super.clone();
            //对引用类型的属性DeepCloneableTarget进行单独处理
            DeepProtoType deepProtoType = (DeepProtoType) deep;
            deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
            return deepProtoType;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //序列化实现深拷贝
    public Object deepClone() throws IOException {
        ByteArrayOutputStream baos=null;
        ByteArrayInputStream bais=null;
        try{
            baos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(this);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            return o;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (bais!=null)
                bais.close();
            if (baos!=null)
                baos.close();
        }
    }

    @Override
    public String toString() {
        return "DeepProtoType{" +
                "name='" + name + '\'' +
                ", deepCloneableTarget=" + deepCloneableTarget +
                '}';
    }
}
