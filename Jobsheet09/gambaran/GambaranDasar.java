package gambaran;

public class GambaranDasar {
    interface Vegetarian {
    }

    static class Animal {
    }

    static class Deer extends Animal implements Vegetarian {
    }

    static class Lion extends Animal {
    }

    public static void main(String[] args) {
        Deer d = new Deer();
        Lion l = new Lion();
        Animal a = d; // valid: Deer adalah Animal
        Animal a2 = l; // valid: Lion adalah Animal
        Vegetarian v = d; // valid: Deer implements Vegetarian
        // Vegetarian v2 = l; // tidak valid: Lion tidak implements Vegetarian

        System.out.println("a menunjuk objek " + a.getClass().getSimpleName());
        System.out.println("a2 menunjuk objek " + a2.getClass().getSimpleName());
        System.out.println("v menunjuk objek " + v.getClass().getSimpleName());
    }
}
