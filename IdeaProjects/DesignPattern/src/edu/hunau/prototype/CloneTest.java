package edu.hunau.prototype;

import java.io.IOException;

public class CloneTest {
    public static void main(String[] args) throws IOException {
//        DeepProtoType deepProtoType = new DeepProtoType();
//        deepProtoType.deepCloneableTarget=new DeepCloneableTarget("波多野结衣","新垣结衣");
//        deepProtoType.name="xxxooo";
//        DeepProtoType clone = (DeepProtoType) deepProtoType.clone();
//        clone.deepCloneableTarget.setCloneName("小泽玛利亚");
//        System.out.println(deepProtoType);
//        System.out.println(clone);
//        System.out.println(deepProtoType.hashCode()+"==="+clone.hashCode());
        DeepProtoType deepProtoType = new DeepProtoType();
        deepProtoType.deepCloneableTarget=new DeepCloneableTarget("波多野结衣","新垣结衣");
        deepProtoType.name="xxxooo";
        Object o = deepProtoType.deepClone();
        System.out.println(deepProtoType+"\n"+o);
        System.out.println(deepProtoType.hashCode()+"\n"+o.hashCode());
    }
}
