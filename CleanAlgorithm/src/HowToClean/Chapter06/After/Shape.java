package HowToClean.Chapter06.After;

public interface Shape {
    public double area();
}
/*
새로운 모양을 추가해도 class 하나만 생성하고 shape의 내용을 구현하게 만들면 된다.
이러한 방식으로 SOLID 중, OCP(Open Close Principle)에 만족하게 된다.
*/