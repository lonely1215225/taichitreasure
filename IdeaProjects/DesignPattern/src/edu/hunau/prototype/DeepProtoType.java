package edu.hunau.prototype;

import java.io.*;

public class DeepProtoType implements Serializable, Cloneable {
    public String name;
    public DeepCloneableTarget deepCloneableTarget;

    //���1��

    @Override
    protected Object clone() {
        try {
            Object deep = null;

            //����Ի����������ͺ�String���͵�clone,��Ϊobject���Ի�ȡthis������clone����ȡthis�Ķ���ͷ��Ϣ
            deep = super.clone();
            //���������͵�����DeepCloneableTarget���е�������
            DeepProtoType deepProtoType = (DeepProtoType) deep;
            deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
            return deepProtoType;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //���л�ʵ�����
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
