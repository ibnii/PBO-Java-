package Tugas;

public class EncapTest {
    public static void main(String[] args) {
        EncapDemo encap = new EncapDemo();
        encap.setName("James");
        encap.setAge(35);

        System.out.println("Name : " + encap.getName());
        System.out.println("Age  : " + encap.getAge());

        // Pengujian batas minimal & rentang nilai (Tugas 3)
        System.out.println("\n--- Pengujian Batas Nilai Umur (Tugas 3) ---");
        encap.setAge(15);
        System.out.println("Set umur 15 (di bawah 18) -> Age: " + encap.getAge());

        encap.setAge(25);
        System.out.println("Set umur 25 (rentang 18-30) -> Age: " + encap.getAge());

        encap.setAge(40);
        System.out.println("Set umur 40 (di atas 30) -> Age: " + encap.getAge());
    }
}
