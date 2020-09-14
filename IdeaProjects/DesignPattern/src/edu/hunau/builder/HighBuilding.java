package edu.hunau.builder;

public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("��¥��ػ�100��");
    }

    @Override
    public void buildWalls() {
        System.out.println("��¥��ǽ100��");
    }

    @Override
    public void roofed() {
        System.out.println("��¥�ݶ���ש����");
    }
}
