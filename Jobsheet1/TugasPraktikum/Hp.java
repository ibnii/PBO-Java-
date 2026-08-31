package TugasPraktikum;

import java.util.Scanner;

public class Hp extends Laptop {
    private String tujuanTelfon;
    String touchScreenStatus = "Normal";

    Hp(){
        super();
        super.jenisLayar = "TN Panel";
    }

    public void setTujuanTelfon(String tujuanTelfon) {
        this.tujuanTelfon = tujuanTelfon;
    }

    void touchscreenCheck() {
        System.out.println("Touchscreen\t: " + touchScreenStatus);
    }

    void telfon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nomer tujuan : ");
        setTujuanTelfon(sc.nextLine());
        System.out.println(". . . . . . Menghubungi " + tujuanTelfon);
    }




}
