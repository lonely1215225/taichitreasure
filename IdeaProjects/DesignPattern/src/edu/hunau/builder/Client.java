package edu.hunau.builder;

public class Client {
    public static void main(String[] args) {
        //����Ʒ����ʹ�����Ʒ�Ĺ��̽������ocpԭ��
        CommonHouse commonHouse=new CommonHouse();
        HouseDirector houseDirector=new HouseDirector(commonHouse);
        houseDirector.constructHouse();

        houseDirector.setHouseBuilder(new HighBuilding());
        houseDirector.constructHouse();

    }


}
