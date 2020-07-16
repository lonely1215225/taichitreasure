package edu.hunau.lab.object;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 任务一、 二维形状对象（Shape）设计与测试
 * 测试Shape类
 */
public class TestShape {
    public static void main(String[] args) {
        Shape[] shapes={new Square( 1, 2 ), new Square( 1, 5 ), new Square( 2, 2 ),
                new Circle( 5 ), new Circle( 2 ), new Circle( 1 )};

        //ShapeComparatorImpl实现接口ShapeComparator的自定义方法
        //使用Arrays工具中的sort方法，参数一：Shape数组
        // 参数二：实现接口ShapeComparator的对象：ShapeComparatorImpl
        //Arrays.sort( shapes, new ShapeComparatorImpl() );

        //使用lambda表达式简化，无需创建类：ShapeComparator和ShapeComparatorImpl
//        Arrays.sort( shapes, (s1, s2) -> {//使用lambda表达式Comparator
//            double area1=s1.area();
//            double area2=s2.area();
//            if (area1> area2)
//                return 1;
//            else if(area1==area2)
//                return 0;
//            else
//                return -1;
//        } );

        //使用匿名内部类
        Arrays.sort( shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                double area1=s1.area();
                double area2=s2.area();
                if (area1> area2)
                    return 1;
                else if(area1==area2)
                    return 0;
                else
                    return -1;
            }
        } );
        //打印结果观察
        for (Shape shape : shapes)
            System.out.println( shape.area() );

        //排序前：
//        2.0
//        5.0
//        4.0
//        78.53981633974483
//        12.566370614359172
//        3.141592653589793

        //排序后：
//        2.0
//        3.141592653589793
//        4.0
//        5.0
//        12.566370614359172
//        78.53981633974483

    }
}

//interface ShapeComparator extends Comparator<Shape> { }
//
//class ShapeComparatorImpl implements ShapeComparator {
//    @Override
//    public int compare(Shape s1, Shape s2) {
//        double area1=s1.area();
//        double area2=s2.area();
//        if (area1 > area2)
//            return 1;
//        else if (area1 == area2)
//            return 0;
//        else
//            return -1;
//    }
//}

/**
 * 父类形状类
 */
interface Shape {
    double area( );//接口中默认为public abstract

}

/**
 * 子类矩形类
 */
class Square implements Shape {
    private double len;
    private double width;

    public Square(double len, double width) {
        this.len=len;
        this.width=width;
    }
    //重写Shape的area()方法
    @Override
    public double area( ) {
        return len * width;
    }
}

/**
 * 子类圈圈类
 */
class Circle implements Shape {
    private double r;

    public Circle(double r) {
        this.r=r;
    }
//重写Shape的area()方法
    @Override
    public double area( ) {
        return Math.PI * r * r;
    }
}

/**
 *和矩形类一毛一样...
 */
class Rectangle implements Shape{
private double len;
private  double width;

    public Rectangle(double len, double width) {
        this.len=len;
        this.width=width;
    }

    @Override
    public double area( ) {
        return len * width;
    }
}