package tugas1;

public class Dosen extends Pegawai {
    protected int jumlahSKS;
    protected static final int TARIF_SKS = 100000;

    public Dosen(String nip, String nama, String alamat) {
        super(nip, nama, alamat);
    }

    public void setSKS(int jumlahSKS) {
        this.jumlahSKS = jumlahSKS;
    }

    @Override
    public int getGaji() {
        return super.getGaji() + (jumlahSKS * TARIF_SKS);
    }
}
