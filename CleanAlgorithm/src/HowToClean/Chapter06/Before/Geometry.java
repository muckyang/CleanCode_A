package HowToClean.Chapter06.Before;

public class Geometry {

    public final double PI = 3.141592;

    public double area(Object shape) throws Exception{
        if(shape instanceof Circle){
            Circle c = (Circle) shape;
            return c.radius * c.radius *PI;
        }else if(shape instanceof Square){
            Square s = (Square) shape;
            return s.side * s.side;
        }else if(shape instanceof Rectangle){
            Rectangle r = (Rectangle) shape;
            return r.height * r.width ;
        }
        throw new NoSuchShapeException("사용할 수 없는 모양 입니다.");
    }
}
/*
모양이 추가될 때 마다 매 번 구현을 변경 해야 한다.
 */
