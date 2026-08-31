package TugasPraktikum;

public class Kasur {
    int panjang;
    int lebar;

    public Kasur(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    void panjangKasur(){
        System.out.println("Panjang = " + panjang);
    }

    void lebarKasur(){
        System.out.println("Lebar = " + lebar);
    }

    void LuasKasur(){
        System.out.println("Luas = " + (panjang*lebar));
    }
}
