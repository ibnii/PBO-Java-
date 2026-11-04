package TugasPraktikum;

public class Laptop {
    String jenisLayar;
    
    public Laptop() {
        this.jenisLayar = "IPS";
    }

    String baterai = "kwh";

    void informasiLayar(){
        System.out.println("Jenis Layar\t: " + jenisLayar);
    }

    void on (){
        System.out.println("Laptop di hidupkan");
    }

    void off (){
        System.out.println("Laptop di matikan");
    }
}
