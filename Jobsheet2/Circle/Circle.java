package Circle;

public class Circle {
    double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    double area(){
        return Math.PI * radius * radius;
    }
    double circumference(){
        return  2*Math.PI*radius;
    }

}
