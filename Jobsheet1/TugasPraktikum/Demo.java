package TugasPraktikum;

public class Demo {
    public static void main(String[] args) {
        Hp hp = new Hp();
        Kasur kasur = new Kasur(15, 10);
        Komputer komputer = new Komputer("Arc B580");
        Laptop laptop = new Laptop();

        System.out.println("========  HP  ========");
        hp.on();
        hp.off();
        hp.informasiLayar();
        hp.telfon();
        hp.touchscreenCheck();

        System.out.println("========  Kasur  ========");
        kasur.LuasKasur();
        kasur.lebarKasur();
        kasur.panjangKasur();

        System.out.println("========  Komputer  ========");
        komputer.on();
        komputer.off();
        komputer.informasiLayar();
        komputer.Gpu();
        komputer.setup();

        System.out.println("========  Laptop  ========");
        laptop.on();
        laptop.off();
        laptop.informasiLayar();



    }
}
