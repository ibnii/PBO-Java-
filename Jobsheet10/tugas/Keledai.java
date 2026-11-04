package tugas;

public class Keledai extends Binatang implements IHerbivora {
    public Keledai(String nama, int jumlahKaki, String suara, String warnaBulu) {
        super(nama, jumlahKaki, suara, warnaBulu);
    }

    @Override
    public void displayMakan() {
        System.out.println("Jenis: Herbivora");
        System.out.println("Makanan: Tumbuhan");
    }
}
