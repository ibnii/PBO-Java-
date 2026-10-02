package tugas1;

public class DaftarGaji {
    private Pegawai[] listPegawai;
    private int jumlah;

    public DaftarGaji(int kapasitas) {
        this.listPegawai = new Pegawai[kapasitas];
        this.jumlah = 0;
    }

    public void addPegawai(Pegawai p) {
        if (jumlah < listPegawai.length) {
            listPegawai[jumlah] = p;
            jumlah++;
        } else {
            System.out.println("Daftar pegawai sudah penuh!");
        }
    }

    public void printSemuaGaji() {
        for (int i = 0; i < jumlah; i++) {
            System.out.println(listPegawai[i].getNama() + " : " + listPegawai[i].getGaji());
        }
    }
}
