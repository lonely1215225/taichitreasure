package edu.hunau.builder;

public class Client {
    public static void main(String[] args) {
        //将产品本身和创建产品的过程解耦，符合ocp原则
        CommonHouse commonHouse=new CommonHouse();
        HouseDirector houseDirector=new HouseDirector(commonHouse);
        houseDirector.constructHouse();

        houseDirector.setHouseBuilder(new HighBuilding());
        houseDirector.constructHouse();

    }


}
