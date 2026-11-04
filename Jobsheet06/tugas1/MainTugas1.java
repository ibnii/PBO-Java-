package tugas1;

public class MainTugas1 {
    public static void main(String[] args) {
        DaftarGaji daftarGaji = new DaftarGaji(10);

        Pegawai p = new Pegawai("P001", "Budi", "Malang");
        Dosen d = new Dosen("D001", "Siti", "Surabaya");
        d.setSKS(12);

        daftarGaji.addPegawai(p);
        daftarGaji.addPegawai(d);

        daftarGaji.printSemuaGaji();
    }
}
