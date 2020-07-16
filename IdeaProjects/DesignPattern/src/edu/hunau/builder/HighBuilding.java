package edu.hunau.builder;

public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("高楼打地基100米");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌墙100米");
    }

    @Override
    public void roofed() {
        System.out.println("高楼屋顶红砖绿瓦");
    }
}
