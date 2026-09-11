package Motor;
public class Motor {
    public int kecepatan = 0;
    public boolean kontakon = false;

    public void printStatus() {
        if (kontakon == true) {
            System.out.println("Kontak On");
        } else {
            System.out.println("Kontak Off");
            System.out.println("Kecepatan " + kecepatan + "\n");
        }
    }
}