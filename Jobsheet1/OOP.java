public class OOP {
    public static void main(String[] args) {

        Product product1 = new Product("Coffee", 120000.0f);
        Product product2 = new Product("Sugar", 30000.0f);

        System.out.println(product1.getName() + ": " + product1.total(3));
        System.out.println(product2.getName() + ": " + product2.total(1));
    }
}