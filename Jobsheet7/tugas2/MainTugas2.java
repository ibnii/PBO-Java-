package tugas2;

public class MainTugas2 {
    public static void main(String[] args) {
        Manusia manusia = new Manusia();
        Manusia dosen = new Dosen();
        Manusia mahasiswa = new Mahasiswa();

        System.out.println("== manusia (Manusia) ==");
        manusia.bernafas();
        manusia.makan();

        System.out.println("\n== dosen (referensi Manusia -> objek Dosen) ==");
        dosen.bernafas();
        dosen.makan();

        System.out.println("\n== mahasiswa (referensi Manusia -> objek Mahasiswa) ==");
        mahasiswa.bernafas();
        mahasiswa.makan();

        System.out.println("\n== method spesifik subclass (downcasting) ==");
        ((Dosen) dosen).lembur();
        ((Mahasiswa) mahasiswa).tidur();

        System.out.println("\n== method yang TIDAK di-override ==");
        dosen.bernafas();
        mahasiswa.bernafas();
    }
}
