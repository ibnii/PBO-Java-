//Nama : Ibni Andarta
//NIM  : 254107020258
//Kelas: TI-2G

public class Reservasi {
    private String kodeReservasi;
    private int jumlahTiket;

    public Reservasi(String kodeReservasi, int jumlahTiket) {
        this.kodeReservasi = kodeReservasi;
        this.jumlahTiket = jumlahTiket;
    }

    public String getKodeReservasi() {
        return kodeReservasi;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public Studio getStudio(Studio studio) {
        return studio;
    }

    public Operator getOperator(Operator operator) {
        return operator;
    }

    public int hitungBiaya(int durasiSewa, Operator operator, Studio studio) {
        int biayaStudio = studio.getTarifSewaPerJam() * durasiSewa;
        int biayaLayanan = operator.getBiayaLayananPerTiket() * jumlahTiket;
        return biayaStudio + biayaLayanan;
    }
}
