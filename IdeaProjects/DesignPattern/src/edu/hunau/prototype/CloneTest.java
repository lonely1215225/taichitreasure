package edu.hunau.prototype;

import java.io.IOException;

public class CloneTest {
    public static void main(String[] args) throws IOException {
//        DeepProtoType deepProtoType = new DeepProtoType();
//        deepProtoType.deepCloneableTarget=new DeepCloneableTarget("����Ұ����","��ԫ����");
//        deepProtoType.name="xxxooo";
//        DeepProtoType clone = (DeepProtoType) deepProtoType.clone();
//        clone.deepCloneableTarget.setCloneName("С��������");
//        System.out.println(deepProtoType);
//        System.out.println(clone);
//        System.out.println(deepProtoType.hashCode()+"==="+clone.hashCode());
        DeepProtoType deepProtoType = new DeepProtoType();
        deepProtoType.deepCloneableTarget=new DeepCloneableTarget("����Ұ����","��ԫ����");
        deepProtoType.name="xxxooo";
        Object o = deepProtoType.deepClone();
        System.out.println(deepProtoType+"\n"+o);
        System.out.println(deepProtoType.hashCode()+"\n"+o.hashCode());
    }
}
