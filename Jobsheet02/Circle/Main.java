package Circle;

public class Main {
    public static void main(String[] args) {
        Circle c = new Circle(5);

        System.out.println("Radius\t\t: " + c.radius);
        System.out.println("Area\t\t: " + c.area());
        System.out.println("Circumference\t: " + c.circumference());
    }
    
}
