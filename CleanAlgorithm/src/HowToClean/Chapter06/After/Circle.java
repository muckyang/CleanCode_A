package HowToClean.Chapter06.After;

public class Circle implements Shape {
    private double radius;
    private final double PI = 3.141592;
    @Override
    public double area(){
        return PI * radius* radius;
    }

}
