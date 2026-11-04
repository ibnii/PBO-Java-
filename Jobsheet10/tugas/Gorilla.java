package tugas;

public class Gorilla extends Binatang implements IKarnivora, IHerbivora {
    public Gorilla(String nama, int jumlahKaki, String suara, String warnaBulu) {
        super(nama, jumlahKaki, suara, warnaBulu);
    }

    @Override
    public void displayMakan() {
        System.out.println("Jenis: Karnivora + Herbivora");
        System.out.println("Makanan: Daging + Tumbuhan");
    }
}
