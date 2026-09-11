package Tugas;

public class Tiket {
    private String judulFilm;
    private double hargaDasar;
    private boolean statusPembayaran;

    public Tiket(String judulFilm, double hargaDasar) {
        this.judulFilm = judulFilm;
        
        // Aturan: hargaDasar tidak boleh negatif. Jika < 0, diset default Rp 35.000
        if (hargaDasar < 0) {
            this.hargaDasar = 35000.0;
        } else {
            this.hargaDasar = hargaDasar;
        }
        
        // Status pembayaran awal selalu false (Belum Dibayar)
        this.statusPembayaran = false;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    // Read-only getter untuk status pembayaran (tidak ada setter langsung dari luar kelas)
    public boolean isStatusPembayaran() {
        return statusPembayaran;
    }

    // Method resmi untuk mengubah status pembayaran
    public void lakukanPembayaran() {
        this.statusPembayaran = true;
    }
}
