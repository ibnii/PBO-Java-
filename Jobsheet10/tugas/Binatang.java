package tugas;

public abstract class Binatang {
    private String nama;
    private int jumlahKaki;
    private String suara;
    private String warnaBulu;

    public Binatang(String nama, int jumlahKaki, String suara, String warnaBulu) {
        this.nama = nama;
        this.jumlahKaki = jumlahKaki;
        this.suara = suara;
        this.warnaBulu = warnaBulu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahKaki() {
        return jumlahKaki;
    }

    public void setJumlahKaki(int jumlahKaki) {
        this.jumlahKaki = jumlahKaki;
    }

    public String getSuara() {
        return suara;
    }

    public void setSuara(String suara) {
        this.suara = suara;
    }

    public String getWarnaBulu() {
        return warnaBulu;
    }

    public void setWarnaBulu(String warnaBulu) {
        this.warnaBulu = warnaBulu;
    }

    public abstract void displayMakan();

    public void displayData() {
        System.out.println("Nama: " + nama);
        System.out.println("Jumlah Kaki: " + jumlahKaki);
        System.out.println("Suara: " + suara);
        System.out.println("Warna Bulu: " + warnaBulu);
    }
}
