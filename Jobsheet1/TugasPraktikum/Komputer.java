package TugasPraktikum;

public class Komputer extends Laptop {
    String gpu;
    String setupStatus = "Setup Succesfully";

    public Komputer(String gpu) {
        super();
        super.jenisLayar = "OLED";
        this.gpu = gpu;
    }

    @Override
    void informasiLayar() {

        jenisLayar = "OLED";
        System.out.println("Informasi Layar\t: " + jenisLayar + " 100%SRGB");
    }

    void Gpu() {
        System.out.println("Gpu\t: " + gpu);
    }

    void setup() {
        System.out.println(setupStatus);
    }

}
