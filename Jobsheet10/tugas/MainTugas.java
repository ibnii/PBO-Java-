package tugas;

public class MainTugas {
    public static void main(String[] args) {
        Binatang[] daftarBinatang = {
                new Keledai("Keledai", 4, "Hehehehe", "Abu-Abu"),
                new Gorilla("Gulali", 4, "Haaum Hauum", "Hitam Manis"),
                new Singa("CingaCing", 4, "Roaar Roaaar", "Coklat")
        };

        for (Binatang binatang : daftarBinatang) {
            binatang.displayMakan();
            binatang.displayData();
            System.out.println();
        }
    }
}
