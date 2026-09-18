package Jobsheet4.GerbongKereta;

public class MainPertanyaan4 {
    public static void main(String[] args) {
        Penumpang p = new Penumpang("12345", "Mr. Krab");
        Gerbong gerbong = new Gerbong("A", 10);
        gerbong.setPenumpang(p, 1);

        // Pertanyaan 4 & 5: Instansiasi budi dan coba tempatkan di kursi nomor 1 yang sudah diduduki Mr. Krab
        Penumpang budi = new Penumpang("67890", "Budi");
        gerbong.setPenumpang(budi, 1);

        System.out.println(gerbong.info());
    }
}
